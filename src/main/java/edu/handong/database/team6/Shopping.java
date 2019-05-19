package edu.handong.database.team6;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Shopping {
	
	private static final int PRODUCT = 1;
	private static final int BRAND = 2;
	private static final int MALL = 3;
    private static final int SEARCH= 4;
    private static final int ORDER = 5;
	private static final int MYORDER = 6;
	private static final int MYPAYMENT = 7;
	private static final int CART = 8;
	private static final int MYCART = 9;
	private static final int DELETEITEM= 10;
	private static final int ALLDELETE= 11;
	private static final int DIBS = 12;
	private static final int MYDIBS = 13;
	private static final int DELETEDIBS = 14;
	private static final int UPDATE = 15;
	private static final int HOME = 16;
	int menuOption = 1 ; 
	
	static BufferedReader rs = new BufferedReader(new InputStreamReader(System.in));
	static DBA db = new DBA();
	PreparedStatement pstmt = null;
	
	void printShoppingMenu() throws NumberFormatException, IOException, SQLException {
		
		while(menuOption != 0) {
	        System.out.println("\n\n\tSHOPPING ");
	        System.out.println(" ------------------------");
	        System.out.println(" 1. Print Products ");
	        System.out.println(" 2. Print Brand ");
	        System.out.println(" 3. Print Shopping Mall ");
	        System.out.println(" 4. Search Products ");
	        System.out.println(" 5. Order ");
	        System.out.println(" 6. Print my Order");
	        System.out.println(" 7. Print my payment");
	        System.out.println(" 8. put in my Shopping Cart");
	        System.out.println(" 9. Print my Shopping Cart");
	        System.out.println(" 10. Delete an item from a shopping Cart");
	        System.out.println(" 11. Delete All Products in Cart");
	        System.out.println(" 12. Dibs");
	        System.out.println(" 13. Print my dibs ");
	        System.out.println(" 14. Delete an itme from Dibs");
	        System.out.println(" 15. Update");
	        System.out.println(" 16. End Shopping");
	        System.out.println(" ------------------------");
	        System.out.println(" Select the Menu : "); 
	      
	        menuOption = Integer.parseInt(rs.readLine());
	        
	        while(menuOption!=HOME) {
        		
        		if( menuOption == PRODUCT) {
        			printProducts(db);
        		}
        		else if( menuOption == BRAND) {
        			printBrand(db);
        		}
        		else if( menuOption == MALL) {
        			printMall(db);
        		}
        		else if( menuOption == SEARCH) {
        			searchProduct(db, rs);
        		}
        		else if( menuOption == ORDER) {
        			System.out.println("Enter the product id");
        			int product_id = Integer.parseInt(rs.readLine());
        			System.out.println("How many do you want to buy?");
        			int number = Integer.parseInt(rs.readLine());
        			System.out.println("Enter your address");
        			String address = rs.readLine();
        			System.out.println("Enter your phone number");
        			String phone = rs.readLine();
        			Order user = new Order(Main.user_id,product_id,number,address,phone);
        			order(user);
        			
        			System.out.println("Would you like to continue the payment?(Y/N)");
        			String answer = rs.readLine(); 
        			if(answer.equals("Y")) {
        				System.out.println("Enter your CreditCardNumber");
            			String card = rs.readLine();
            			System.out.println("Enter your CreditCardPassword");
            			String password = rs.readLine();
        				Payment(Main.user_id,product_id,number,card,password);
        			}
        		}
        		else if( menuOption == MYORDER) {
        			ShowOrdering(Main.user_id);
        		}
        		else if( menuOption == MYPAYMENT) {
        			PrintPayment(Main.user_id);
        		}
        		
        		else if( menuOption == CART) {
        			System.out.println("Enter the product id");
        			int product_id = Integer.parseInt(rs.readLine());
        			System.out.println("How many do you want to put?");
        			int number = Integer.parseInt(rs.readLine());
        			AddCart(Main.user_id,product_id,number);
        		}
        		else if( menuOption == MYCART) {
        			ShowCart(Main.user_id);
        		}
        		else if( menuOption == DELETEITEM) {
        			System.out.println("\tEnter the product id");
        			int product_id = Integer.parseInt(rs.readLine());
        			DeleteCart(Main.user_id,product_id);
        		}
        		else if( menuOption == ALLDELETE) {
        			CleanCart(Main.user_id);
        		}
        		else if( menuOption == DIBS) {
        			System.out.println("\tEnter the product id");
        			int product_id = Integer.parseInt(rs.readLine());
        			AddDibs(Main.user_id,product_id);
        		}
        		else if( menuOption == MYDIBS) {
        			ShowDibs(Main.user_id);
        		}
        		else if( menuOption == DELETEDIBS) {
        			System.out.println("Enter the product id");
        			int product_id = Integer.parseInt(rs.readLine());
        			DeleteDibs(Main.user_id,product_id);
        		}
        		else if( menuOption == UPDATE) {
        			Update();
        		}
        		else if( menuOption == HOME) {
        			continue;
        		}
        		printShoppingMenu();
	        }
	        break;
		}
	    }
	
	// 상품전체 목록 출력 
	public static void printProducts(DBA db) {
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT *FROM product";
			stmt = DBA.con.prepareStatement(query);
			rset = stmt.executeQuery();
			
			System.out.println("productId    productName               prcie            type           brand      inventory            date                 mall");
			System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
			while(rset.next()) {
				int productID = rset.getInt(1);
				String productName = rset.getString(2);
				int price = rset.getInt(3);
				String type = rset.getString(4);
				String brand = rset.getString(5);
				int inventory = rset.getInt(6);
				Timestamp date = rset.getTimestamp(7);
				String mall = rset.getString(8);
				System.out.format("%5d  %20s  %15d %15s %15s %10d %30s %15s\n",productID,productName,price,type,brand,inventory,date,mall);
				
			}
			int menu = 0;
			while(menu!=3) {
				System.out.println("\n\n");
				System.out.println("------------------------");
				System.out.println("1. Sort by Latest");
				System.out.println("2. Sort by Price");
				System.out.println("3. Exit products");
				System.out.println("------------------------");
				System.out.println("Select the Menu :");
				menu = Integer.parseInt(rs.readLine());
				
				switch(menu) {
				
				//최신순으로 보기 
				case 1:
					query = "SELECT *FROM product ORDER BY date DESC";
					stmt = DBA.con.prepareStatement(query);
					rset = stmt.executeQuery();
					
					System.out.println("productId    productName               prcie            type           brand      inventory            date                 mall");
					System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
					while(rset.next()) {
						int productID = rset.getInt(1);
						String productName = rset.getString(2);
						int price = rset.getInt(3);
						String type = rset.getString(4);
						String brand= rset.getString(5);
						int inventory = rset.getInt(6);
						Timestamp date = rset.getTimestamp(7);
						String mall = rset.getString(8);
						System.out.format("%5d  %20s  %15d %15s %15s %10d %30s %15s\n",productID,productName,price,type,brand,inventory,date,mall);
						
					}
					break;
					
				//가격 낮은 순으로 보기 
				case 2:
					query = "SELECT *FROM product ORDER BY price";
					stmt = DBA.con.prepareStatement(query);
					rset = stmt.executeQuery();
					
					System.out.println("productId    productName               prcie            type           brand      inventory            date                 mall");
					System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
					while(rset.next()) {
						int productID = rset.getInt(1);
						String productName = rset.getString(2);
						int price = rset.getInt(3);
						String type = rset.getString(4);
						String brand = rset.getString(5);
						int inventory = rset.getInt(6);
						Timestamp date = rset.getTimestamp(7);
						String mall = rset.getString(8);
						System.out.format("%5d  %20s  %15d %15s %15s %10d %30s %15s\n",productID,productName,price,type,brand,inventory,date,mall);
					}
					break;
				case 3:
					break;
				}
			}
		}catch(Exception e) {
			
		}
	}
	
	//브랜드 보기
	public static void printBrand(DBA db) {
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT DISTINCT brand FROM product";
			stmt = DBA.con.prepareStatement(query);
			rset = stmt.executeQuery();
			
			System.out.println("Brnad Name");
			System.out.println("--------------");
			while(rset.next()) {
				String brand = rset.getString(1);
				System.out.format("%5s\n" ,brand);
			}
			System.out.println("--------------");
		}catch (Exception e) {
			
		}
	}
	
	//쇼핑몰만 보기 
	public static void printMall(DBA db) {
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT DISTINCT mall FROM product";
			stmt = DBA.con.prepareStatement(query);
			rset = stmt.executeQuery();
			
			System.out.println("Shopping Mall");
			System.out.println("--------------");
			while(rset.next()) {
				String mall = rset.getString(1);
				System.out.format("%5s\n" ,mall);	
			}
			System.out.println("--------------");
		}catch (Exception e) {
			
		}
	}
	
		
	// 상품검색 
		public static void searchProduct(DBA db, BufferedReader rs) {
			PreparedStatement stmt = null;
			ResultSet rset = null;
			int menu;
			String Search,query;
			try {
				
				System.out.println("\t Select search keyword ");
				System.out.println("------------------------");
				System.out.println("1. Product Name");
				System.out.println("2. Product Type");
				System.out.println("3. Brand");
				System.out.println("4. Price Range");
				System.out.println("------------------------");
				
				menu = Integer.parseInt(rs.readLine());
				switch(menu) {
				
				//이름으로 검색 
				case 1:
					System.out.println("Please enter the product you want to find : ");
					Search = rs.readLine();
					query = "SELECT *FROM product WHERE productName like '%" + Search + "%'";
					stmt = DBA.con.prepareStatement(query);
					rset = stmt.executeQuery();
					
					System.out.println("productId    productName               prcie            type           brand      inventory            date                 mall");
					System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
					while(rset.next()) {
						int productID = rset.getInt(1);
						String productName = rset.getString(2);
						int price = rset.getInt(3);
						String type = rset.getString(4);
						String brand = rset.getString(5);
						int inventory = rset.getInt(6);
						Timestamp date = rset.getTimestamp(7);
						String mall = rset.getString(8);
						System.out.format("%5d  %20s  %15d %15s %15s %10d %30s %15s\n",productID,productName,price,type,brand,inventory,date,mall);
					
					}
					break;
					
				//타입으로 검색 
				case 2:
					
					System.out.println("Please enter the type you want to find : ");
					Search = rs.readLine();
					query = "SELECT *FROM product WHERE type like '%" + Search + "%'";
					stmt = DBA.con.prepareStatement(query);
					rset = stmt.executeQuery();
					
					System.out.println("productId    productName               prcie            type           brand      inventory            date                 mall");
					System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
					while(rset.next()) {
						int productID = rset.getInt(1);
						String productName = rset.getString(2);
						int price = rset.getInt(3);
						String type = rset.getString(4);
						String brand = rset.getString(5);
						int inventory = rset.getInt(6);
						Timestamp date = rset.getTimestamp(7);
						String mall = rset.getString(8);
						System.out.format("%5d  %20s  %15d %15s %15s %10d %30s %15s\n",productID,productName,price,type,brand,inventory,date,mall);
					}
					break;
					
				//브랜드로 검색 
				case 3: 

					System.out.println("Please enter the type you want to find : ");
					Search = rs.readLine();
					query = "SELECT *FROM product WHERE brand like '%" + Search + "%'";
					stmt = DBA.con.prepareStatement(query);
					rset = stmt.executeQuery();
					
					System.out.println("productId    productName               prcie            type           brand      inventory            date                 mall");
					System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
					while(rset.next()) {
						int productID = rset.getInt(1);
						String productName = rset.getString(2);
						int price = rset.getInt(3);
						String type = rset.getString(4);
						String brand = rset.getString(5);
						int inventory = rset.getInt(6);
						Timestamp date = rset.getTimestamp(7);
						String mall = rset.getString(8);
						System.out.format("%5d  %20s  %15d %15s %15s %10d %30s %15s\n",productID,productName,price,type,brand,inventory,date,mall);
					
					}
					break;
					
				//가격으로 검색 
				case 4:
					System.out.println(" Please set the price : ");
					Search = rs.readLine();
					query = "SELECT *FROM product WHERE price >= " + Search ;
					stmt = DBA.con.prepareStatement(query);
					rset = stmt.executeQuery();
					
					System.out.println("productId    productName               prcie            type           brand      inventory            date                 mall");
					System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
					while(rset.next()) {
						int productID = rset.getInt(1);
						String productName = rset.getString(2);
						int price = rset.getInt(3);
						String type = rset.getString(4);
						String brand = rset.getString(5);
						int inventory = rset.getInt(6);
						Timestamp date = rset.getTimestamp(7);
						String mall = rset.getString(8);
						System.out.format("%5d  %20s  %15d %15s %15s %10d %30s %15s\n",productID,productName,price,type,brand,inventory,date,mall);
					
					}
					break;
					
				//쇼핑몰 검색 
				case 5:
					System.out.println(" Please set the price : ");
					Search = rs.readLine();
					query =  "SELECT *FROM product WHERE mall like '%" + Search + "%'";
					stmt = DBA.con.prepareStatement(query);
					rset = stmt.executeQuery();
					
					System.out.println("productId    productName               prcie            type           brand      inventory            date                 mall");
					System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
					while(rset.next()) {
						int productID = rset.getInt(1);
						String productName = rset.getString(2);
						int price = rset.getInt(3);
						String type = rset.getString(4);
						String brand = rset.getString(5);
						int inventory = rset.getInt(6);
						Timestamp date = rset.getTimestamp(7);
						String mall = rset.getString(8);
						System.out.format("%5d  %20s  %15d %15s %15s %10d %30s %15s\n",productID,productName,price,type,brand,inventory,date,mall);
					
					}
					break;
				}
			}catch(Exception e) {
				
			}
			
			
	
	}
		
		// 상품주문 
		public static void order(Order orderUser) {
			String query = "INSERT INTO ordering(UserId,productId,number,address,phone) VALUES(?,?,?,?,?)";
			PreparedStatement stmt = null;
			try {
				stmt = DBA.con.prepareStatement(query);
				stmt.setInt(1, orderUser.getID());
				stmt.setInt(2,orderUser.getProductId());
				stmt.setInt(3, orderUser.getNumber());
				stmt.setString(4,orderUser.getAddress());
				stmt.setString(5,orderUser.getPhone());
				stmt.executeUpdate();
				System.out.println("Complete to Order!");
				System.out.println("----------------------------------");
				
			}catch(Exception e) {
				System.out.println("\tError!!!");
			}
		}
		
		// 주문목록 확인하기 
		public static void ShowOrdering(int Id) {
			String query = "SELECT *FROM ordering where `UserId` =? ";
			PreparedStatement stmt = null;
			ResultSet rset = null;
			try {
				stmt = DBA.con.prepareStatement(query);
				stmt.setInt(1, Id);
				rset = stmt.executeQuery();
				System.out.println("ID    productID   number           address           phone");
				System.out.println("----------------------------------------------------------------");
				while(rset.next()) {
					int id= rset.getInt(1);
					int productId = rset.getInt(2);
					int number = rset.getInt(3);
					String address = rset.getString(4);
					String phone = rset.getString(5);
					System.out.format("%d %5d %10d %20s %20s\n",id,productId,number, address, phone);
				}
				
			}catch(Exception e) {
				
			}
		}
		
		
		// 장바구니 목록 확인 
		public static void ShowCart(int Id) {
			PreparedStatement stmt = null;
			ResultSet rset = null;
			try {
				String query = "SELECT *FROM ShoppingCart where id = ? AND validity =1 ";
				stmt = DBA.con.prepareStatement(query);
				stmt.setInt(1, Id);
				rset = stmt.executeQuery();
				System.out.println("ID     productID     number      validity");
				System.out.println("--------------------------------------------");
				while(rset.next()) {
					int id = rset.getInt(1);
					int productId = rset.getInt(2);
					int number = rset.getInt(3);
					int validity = rset.getInt(4);
					System.out.format("%d %8d %10d %10d\n",id,productId,number,validity);
					
				}
			}catch(Exception e) {
				
			}
		}
		
		// 장바구니 상품추가 
		public static void AddCart(int userid, int productid, int numbers) {
			PreparedStatement stmt = null;
			
			try {
				String query="INSERT INTO ShoppingCart VALUES(?,?,?,1)";
				stmt = DBA.con.prepareStatement(query);
				stmt.setInt(1,userid);
				stmt.setInt(2,productid);
				stmt.setInt(3,numbers);
				stmt.executeUpdate();
				System.out.println("put the goods in your cart!!");
				System.out.println("----------------------------------");
			}catch(Exception e) {
				System.out.println("\tError!!!\n");
			}
		}
		// 장바구니에서 상품 하나 삭제 
		public static void DeleteCart(int Id, int productId) {
			PreparedStatement stmt = null;
			try {
				String query="UPDATE ShoppingCart SET validity = 2 WHERE id=? AND productId =?";
				stmt = DBA.con.prepareStatement(query);
				stmt.setInt(1, Id);
				stmt.setInt(2, productId);
				stmt.executeUpdate();
				System.out.println("Complete!");
				System.out.println("----------------------------------");
			}catch(Exception e) {
				
			}
		}
		
		// 장바구니 전체 삭제 
		public static void CleanCart(int Id) {
			PreparedStatement stmt = null;
			try {
				String query="UPDATE ShoppingCart SET validity = 2 WHERE id=? ";
				stmt = DBA.con.prepareStatement(query);
				stmt.setInt(1, Id);
				stmt.executeUpdate();
				System.out.println("Complete!");
				System.out.println("----------------------------------");
			}catch(Exception e){
			
			}
		}
		
		// 상품 찜하기
		public static void AddDibs(int userid, int productid) {
			PreparedStatement stmt = null;
			
			try {
				String query="INSERT INTO dibs VALUES(?,?)";
				stmt = DBA.con.prepareStatement(query);
				stmt.setInt(1,userid);
				stmt.setInt(2,productid);
				stmt.executeUpdate();
				System.out.println("DIBS!");
				System.out.println("----------------------------------");
				
			}catch(Exception e) {
				
			}
		}
		// 찜한 상품삭제하기
		public static void DeleteDibs(int Id, int productid) {
			PreparedStatement stmt = null;
			try {
				String query="DELETE FROM dibs WHERE productId=?";
				stmt = DBA.con.prepareStatement(query);
				stmt.setInt(1, Id);
				stmt.setInt(2, productid);
				stmt.executeUpdate();
				System.out.println("Complete to delete");
				System.out.println("----------------------------------");
			}catch(Exception e) {
				
			}
		}
		// 찜한 목록 보기
		public static void ShowDibs(int Id) {
			PreparedStatement stmt = null;
			ResultSet rset = null;
			try {
				String query = "SELECT *FROM dibs where id = ? ";
				stmt = DBA.con.prepareStatement(query);
				stmt.setInt(1, Id);
				rset = stmt.executeQuery();
				System.out.println("id      productId");
				System.out.println("-----------------");
				while(rset.next()) {
					int id = rset.getInt(1);
					int productId = rset.getInt(2);
					System.out.format("%5d %8d\n",id,productId);	
				}
			}catch(Exception e){
				
			}
		}
		
		//결제내역 보기 
		public static void PrintPayment(int Id) {
			PreparedStatement stmt = null;
			ResultSet rset = null;
			try {
				String query = "SELECT *FROM payment where id = ? ";
				stmt = DBA.con.prepareStatement(query);
				stmt.setInt(1, Id);
				rset = stmt.executeQuery();
				System.out.println("ID    productID   number           cardNumber           cardPassword");
				System.out.println("----------------------------------------------------------------------");
				while(rset.next()) {
					int UserId = rset.getInt(1);
					int productId = rset.getInt(2);
					int number = rset.getInt(3);
					String cardNumber = rset.getString(4);
					String cardPassword = rset.getString(5);
					System.out.format("%d %5d %12d %20s %20s\n",UserId,productId,number, cardNumber, cardPassword);
				}
				
			}catch(Exception e) {
				
			}
		}
		
		
		//결제하기
		public static void Payment(int userId, int productId, int numbers, String cardNumber, String cardPassword) {
			PreparedStatement stmt = null;
			try {
				String query2 = "INSERT INTO payment VALUES (?,?,?,?,?)";
				stmt = DBA.con.prepareStatement(query2);
				stmt.setInt(1, userId);
				stmt.setInt(2, productId);
				stmt.setInt(3, numbers);
				stmt.setString(4, cardNumber);
				stmt.setString(5, cardPassword);
				stmt.executeUpdate();
				System.out.println("Complete Payment");
				System.out.println("----------------------------------");
			}catch(Exception e) {
				
			}
		}
		
		public static void Update() throws IOException, SQLException {
			PreparedStatement stmt = null;
			System.out.println("Are You a manager?");
			String answer = rs.readLine(); 
			String query;
			if(answer.equals("Y")){
				int menu;
				System.out.println("Enter the product id of the wrong information : ");
				int productId = Integer.parseInt(rs.readLine());
		
				System.out.println("--------------------------");
				System.out.println("1. Update Product Name");
				System.out.println("2. Update Price");
				System.out.println("3. Update Inventory");
				System.out.println("4. Exit");
				menu = Integer.parseInt(rs.readLine());
				
				switch(menu) {
				//상품 이름 수정 
				case 1:
					System.out.println("Enter the correct Information : ");
					String productName = rs.readLine();
					query="UPDATE product set productName=\""+productName+"\"where productId = " + productId;
					stmt = DBA.con.prepareStatement(query);
					stmt.executeUpdate();
					break;
				//가격 수정 
				case 2:
					System.out.println("Enter the correct Information : ");
					int price = Integer.parseInt(rs.readLine());
					query="UPDATE product set price=\""+price+"\"where productId = " + productId;
					stmt = DBA.con.prepareStatement(query);
					stmt.executeUpdate();
					break;
				//재고 수정 
				case 3:
					System.out.println("Enter the correct Information : ");
					int inventory = Integer.parseInt(rs.readLine());
					query="UPDATE product set inventory=\""+inventory+"\"where productId = " + productId;
					stmt = DBA.con.prepareStatement(query);
					stmt.executeUpdate();
					break;
				}
			}
			else {
				System.out.println("You can not modify");
				System.out.println("---------------------");
			
		}
		}
}
