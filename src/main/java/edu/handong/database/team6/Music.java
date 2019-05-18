package edu.handong.database.team6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Music {
	BufferedReader rs = new BufferedReader(new InputStreamReader(System.in));
	MusicList musicList = new MusicList();
	PreparedStatement pstmt = null;
	
	int menuOption = 1;
	
	Music() throws Exception {
		ResultSet rt;
		
		
		pstmt = DBA.con.prepareStatement("select * from MusicUser where `id`=?");
		pstmt.setInt(1, Main.user_id);
		rt = pstmt.executeQuery();
			
		if(rt.next()) {
			System.out.println(rt.getInt("id"));				
			return ;
		}else {
			pstmt.executeUpdate("INSERT INTO MusicUser(id,myList,count,Voucher) VALUES("+Main.user_id+",NULL,0,NULL);");
		}
		
	}
	
	void seletMenu() throws Exception{
		
		while(menuOption != 0) {
			
			System.out.println("\tNAVER MUSIC");
	        System.out.println(" ------------------------");
	        System.out.println("01. Top 10"); 
	        System.out.println("02. Print All Music List");//
			System.out.println("03. Search Music  (Music title | Artist | Album | Genre | Lyrics)"); //
	        System.out.println("04. Listen Music");//최근 추가, 횟수 ++
	        System.out.println("    - Add Music to MyList");
	        System.out.println("    - Add Music to Favorite List");
	        System.out.println("    - Add Music to Recent List");
	        System.out.println("05. Ask new music "); //
	        System.out.println("06. Ask to modify wrong information (Music title | Artist | Album | Genre | Lyrics | Release Date)");//
	        System.out.println("07. Buy Voucher");//
	        System.out.println();
	        System.out.println("\tMY MUSIC");
	        System.out.println(" ------------------------");
	        System.out.println("08. Make new MyList");//
	        System.out.println("09. Print MyList Music");
	        System.out.println("10. Print Recent Music");
	        System.out.println("11. Pirnt My Favorite");
	        System.out.println("12. Check My Voucher");//
	        
	        System.out.println(" ------------------------");
			System.out.println("0 : Back to main menu ");
			System.out.println(" ------------------------");
			System.out.println(" Select the Menu : ");
			
			
			menuOption = Integer.parseInt(rs.readLine());
			
			switch(menuOption) {
			
			case 0:
				System.out.println("Goodbye~!");
				break;
				
			case 1:
				//만들 함수 music_id를 넣으면 해당하는테이블이 출력 
				
				break;
				
			case 2:
				pirntAllMusicList();
				break;
				
			case 3:
				searchMusicInfo();
				break;
				
			case 4:
				listenMusic();
				break;
				
			case 5:
				addMusic();
				break;
			
			case 6:
				modifyWrongInformation();
				break;
				
			case 7:
				buyVoucher();
				break;
				
			case 8:
				makeNewMyList();
				break;
				
			case 9:
				printMyList(true);
				break;
				
			case 12:
				checkVoucher();
				break;
				
			default :
				System.out.println("Please select again!");
				break;
			
			}
		}
	}
	
	void listenMusic() {
		
	}
	
	
	void printOneMusicInformation() throws SQLException {
		ResultSet rt;
		
		pstmt = DBA.con.prepareStatement("select * from MusicUser where `id`=?");
		pstmt.setInt(1, Main.user_id);
		rt = pstmt.executeQuery();
		
		while(rt.next()) {
			
			
			
			break;
		}
	}
	
	
	void printMyList(boolean printMusic) throws SQLException {
		ResultSet rt;
		
		pstmt = DBA.con.prepareStatement("select * from MusicUser where `id`=?");
		pstmt.setInt(1, Main.user_id);
		rt = pstmt.executeQuery();
		
		System.out.println("\tMyList");
        System.out.println(" ------------------------");
        
		while(rt.next()) {
			String myList = rt.getString("myList");
			
			if(myList == null) {
				System.out.println("There is no myList");
			}else {
				String[] words = myList.split(",");
		         
		        for (String word : words ){
		            System.out.println(word);
		        }
		        
		        if(printMusic == false) {
		        	System.out.println();
		        	return;
		        }

				//유저한테 마이리스트 입력받음
				//마이리스트를 이용하여 해당 마이리스트의 이름과 유저 이름 을 가진 음악들 출력 
			}
			break;
		}
		
		System.out.println();
	}
	
	void makeNewMyList() throws SQLException, IOException {
		BufferedReader rs = new BufferedReader(new InputStreamReader(System.in));
		String new_myList;
		ResultSet rt;
		
		printMyList(false);
		
		System.out.println("Input new MyList name (You can't use ',' symbol) : ");
		
		new_myList = rs.readLine();
		
		pstmt = DBA.con.prepareStatement("select * from MusicUser where `id`=?");
		pstmt.setInt(1, Main.user_id);
		rt = pstmt.executeQuery();
		
		while(rt.next()) {
			
			String myList = rt.getString("myList");
			
			if(myList == null) {
				break;
			}else {
				new_myList = new_myList + "," + myList;
			}
			break;
		}
		
		pstmt.executeUpdate("UPDATE MusicUser set myList='"+new_myList+"' where id = "+ Main.user_id);
		
		printMyList(false);
		
	}
	
	
	
	void checkVoucher() throws Exception {
		ResultSet rt;
		
		
		pstmt = DBA.con.prepareStatement("select * from MusicUser where `id`=?");
		pstmt.setInt(1, Main.user_id);
		rt = pstmt.executeQuery();
		
		
		while(rt.next()) {
			String voucher = rt.getString("Voucher");
			if(voucher != null) {
				System.out.println("You are using a voucher : "+voucher);
			}else {
				System.out.println("Please buy a voucher");
			}
			break;
		}
	}
	
	
	void buyVoucher() throws Exception{
		
		BufferedReader rs = new BufferedReader(new InputStreamReader(System.in));
		ResultSet rt;
		
		pstmt = DBA.con.prepareStatement("select * from MusicUser where `id`=?");
		pstmt.setInt(1, Main.user_id);
		rt = pstmt.executeQuery();
		
		
		while(rt.next()) {
			String voucher = rt.getString("Voucher");
			
			if(voucher != null) {
				System.out.println("You are already using a voucher : "+voucher);
			}
				
				System.out.println("\tVoucher");
		        System.out.println(" ------------------------");
		        System.out.println("1) Unlimited listening					Month :  7,500won");
		        System.out.println("2) Unlimited listening + Store in smartphone		Month : 10,000won");
		        
		        menuOption = Integer.parseInt(rs.readLine());
		        
		        switch(menuOption) {
				
				case 1:
					pstmt.executeUpdate("UPDATE MusicUser set Voucher=\"Unlimited listening\" where id = "+Main.user_id);
					break;
					
				case 2:
					pstmt.executeUpdate("UPDATE MusicUser set Voucher='Unlimited listening + Store in smartphone' where id = "+Main.user_id+";");
					break;
					
				default :
					System.out.println("Please select again!");
					break;
		        }
		        break;
		}
		
        
        
	}
	
	
	void pirntAllMusicList() throws SQLException {
		String query = "select * from MusicList;";
		Statement pstmt;
		ResultSet rt;
		
		pstmt = DBA.con.createStatement();
		
		rt = pstmt.executeQuery(query);
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.format("music_id |	   title	   |   artist_name    |                    album_name	              |        release_date	    |      genre     |    count");
		System.out.println();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");


		while(rt.next()) {
			int music_id = rt.getInt("music_id");
			String title = rt.getString("title");
			String artist_name = rt.getString("artist_name");
			String album_name = rt.getString("album_name");
			String release_date = rt.getString("release_date");
			String genre = rt.getString("genre");
			int count = rt.getInt("count");
			System.out.format("%5s %23s %20s %50s %20s %23s %10s\n",music_id, title, artist_name, album_name, release_date, genre, count);
			System.out.println();
		}
		
	}
	
	
	void addMusic() throws IOException, SQLException {
		String lyrics = " ";
		String line;
		
		System.out.print(" Music title :");
		musicList.setTitle(rs.readLine());
		
		System.out.print("Artist name :");
		musicList.setArtist_name(rs.readLine());
		
		System.out.print("Album name :");
		musicList.setAlbum_name(rs.readLine());
		
		System.out.print("Release Date (yyyy-mm-dd) :");
		musicList.setRelease_date(rs.readLine());
		
		System.out.print("Lyrics (Q! : exit) :");
		line = rs.readLine();
		
		while(!line.equals("Q!")) {
			lyrics += line;
			lyrics += "\n";
			line = rs.readLine();
		}
		musicList.setLyrics(lyrics);
		
		System.out.print("Genre (POP | HIP-HOP | R&B | ROCK/FOLK | DANCE):");
		musicList.setGenre(rs.readLine());
		
		musicList.setCount(0);
		
		String query = "insert into MusicList(artist_name, album_name, release_date, lyrics, title, count, genre) values(?,?,?,?,?,?,?);";
		
		pstmt = DBA.con.prepareStatement(query);
		
		pstmt.setString(1,musicList.getArtist_name());
		pstmt.setString(2,musicList.getAlbum_name());
		pstmt.setString(3,musicList.getRelease_date());
		pstmt.setString(4,musicList.getLyrics());
		pstmt.setString(5,musicList.getTitle());
		pstmt.setInt(6, 0);
		pstmt.setString(7, musicList.getGenre());
		pstmt.executeUpdate();
		
		System.out.println("\nAdd music success ! ");
	}
	
	void modifyWrongInformation() throws NumberFormatException, IOException, SQLException {
		BufferedReader rs = new BufferedReader(new InputStreamReader(System.in));
		int music_id;
		int menuOption;
		ResultSet rt;
		
		pirntAllMusicList();
		System.out.println("\tEnter the music id of the wrong information : ");
		
		music_id = Integer.parseInt(rs.readLine());
		
		System.out.println("\tSelect information category");
        System.out.println(" ------------------------");
        System.out.println(" 1. Music title");
        System.out.println(" 2. Artist name");
        System.out.println(" 3. Album name");
        System.out.println(" 4. Genre");
        System.out.println(" 5. Lyrics");
        System.out.println(" 6. Release Date");
        System.out.println(" ------------------------");
        
        menuOption = Integer.parseInt(rs.readLine());
        

			
			switch(menuOption) {
	        
	        case 1:
	        	
	        	System.out.println("Enter the correct information : ");
	        	String title = rs.readLine();
	        	pstmt.executeUpdate("UPDATE MusicList set title=\""+title+"\" where music_id = "+ music_id);
	        	//id넣으면 하나만 프린트 하는 함수 
	        	break;
	        	
	        case 2:
	        	System.out.println("Enter the correct information : ");
	        	String Artist = rs.readLine();
	        	pstmt.executeUpdate("UPDATE MusicList set artist_name=\""+Artist+"\" where music_id = "+ music_id);
	        	
	        	break;
	        	
	        case 3:
	        	System.out.println("Enter the correct information : ");
	        	String Album = rs.readLine();
	        	pstmt.executeUpdate("UPDATE MusicList set album_name=\""+Album+"\" where music_id = "+ music_id);
	        	
	        	break;
	        	
	        case 4:
	        	System.out.println("Enter the correct information \nGenre (POP | HIP-HOP | R&B | ROCK/FOLK | DANCE) : ");
	        	String Genre = rs.readLine();
	        	pstmt.executeUpdate("UPDATE MusicList set genre=\""+Genre+"\" where music_id = "+ music_id);
	        	
	        	break;
	        	
	        case 5:
	        	System.out.println("Enter the correct information (Q! = exit): ");
	        	String lyrics = "";
	        	String line;
	        	
	        	line = rs.readLine();
	    		
	    		while(!line.equals("Q!")) {
	    			lyrics += line;
	    			lyrics += "\n";
	    			line = rs.readLine();
	    		}
	        	pstmt.executeUpdate("UPDATE MusicList set lyrics=\""+lyrics+"\" where music_id = "+ music_id);
	        	
	        	break;
	        	
	        case 6:
	        	System.out.println("Enter the correct information");
	        	String release_date = rs.readLine();
	        	pstmt.executeUpdate("UPDATE MusicList set release_date=\""+release_date+"\" where music_id = "+ music_id);
	        	
	        	break;
	        	
	        default :
				System.out.println("Please select again!");
				break;
	        
	        
	        
	        }
			
        

        
	}
	
	
	void searchMusicInfo() throws NumberFormatException, IOException, SQLException{
		String searchKeyword;
		String query;
		int menuOption;
		Statement pstmt;
		ResultSet rt;
		
		
		System.out.println("\tSelect search category");
        System.out.println(" ------------------------");
        System.out.println(" 1. Music title");
        System.out.println(" 2. Artist name");
        System.out.println(" 3. Album name");
        System.out.println(" 4. Genre");
        System.out.println(" 5. Lyrics");
        System.out.println(" ------------------------");
		
        menuOption = Integer.parseInt(rs.readLine());
        
        switch(menuOption) {
        
		case 1:
			System.out.print("Please input Music title : ");
			searchKeyword = rs.readLine();
			query = "Select * from MusicList where title like '%"+searchKeyword+"%'";
			pstmt = DBA.con.createStatement();
			
			rt = pstmt.executeQuery(query);
			
			while(rt.next()) {
				int music_id = rt.getInt("music_id");
				String title = rt.getString("title");
				String artist_name = rt.getString("artist_name");
				String album_name = rt.getString("album_name");
				String release_date = rt.getString("release_date");
				String genre = rt.getString("genre");
				int count = rt.getInt("count");
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.format("music_id	     title	     artist_name                        album_name	                   release_date	             genre         count");
				System.out.println();
				System.out.format("%5s %20s %20s %50s %20s %20s %10s\n",music_id, title, artist_name, album_name, release_date, genre, count);
				System.out.println();
			}
			
			break;
			
		case 2:
			System.out.print("Please input Artist name of Music : ");
			searchKeyword = rs.readLine();
			query = "Select * from MusicList where artist_name like '%"+searchKeyword+"%'";
			pstmt = DBA.con.createStatement();
			
			rt = pstmt.executeQuery(query);
			
			
			while(rt.next()) {
				int music_id = rt.getInt("music_id");
				String title = rt.getString("title");
				String artist_name = rt.getString("artist_name");
				String album_name = rt.getString("album_name");
				String release_date = rt.getString("release_date");
				String genre = rt.getString("genre");
				int count = rt.getInt("count");
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.format("music_id	     title	     artist_name                        album_name	                   release_date	             genre         count");
				System.out.println();
				System.out.format("%5s %20s %20s %50s %20s %20s %10s\n",music_id, title, artist_name, album_name, release_date, genre, count);
				System.out.println();
			}
			
			
			break;
			
		case 3:
			System.out.print("Please input Album name of Music : ");
			searchKeyword = rs.readLine();
			query = "Select * from MusicList where album_name like '%"+searchKeyword+"%'";
			pstmt = DBA.con.createStatement();
			
			rt = pstmt.executeQuery(query);
			
			while(rt.next()) {
				int music_id = rt.getInt("music_id");
				String title = rt.getString("title");
				String artist_name = rt.getString("artist_name");
				String album_name = rt.getString("album_name");
				String release_date = rt.getString("release_date");
				String genre = rt.getString("genre");
				int count = rt.getInt("count");
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.format("music_id	     title	     artist_name                        album_name	                   release_date	             genre         count");
				System.out.println();
				System.out.format("%5s %20s %20s %50s %20s %20s %10s\n",music_id, title, artist_name, album_name, release_date, genre, count);
				System.out.println();
			}
			break;
			
		case 4:
			System.out.print("Please input Genre of Music (POP | HIP-HOP | R&B | ROCK/FOLK | DANCE) : ");
			searchKeyword = rs.readLine();
			query = "Select * from MusicList where genre like '%"+searchKeyword+"%'";
			pstmt = DBA.con.createStatement();
			
			rt = pstmt.executeQuery(query);
			
			while(rt.next()) {
				int music_id = rt.getInt("music_id");
				String title = rt.getString("title");
				String artist_name = rt.getString("artist_name");
				String album_name = rt.getString("album_name");
				String release_date = rt.getString("release_date");
				String genre = rt.getString("genre");
				int count = rt.getInt("count");
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.format("music_id	     title	     artist_name                        album_name	                   release_date	             genre         count");
				System.out.println();
				System.out.format("%5s %20s %20s %50s %20s %20s %10s\n",music_id, title, artist_name, album_name, release_date, genre, count);
				System.out.println();
			}
			break;
			
		case 5:
			System.out.print("Please input Music lyrics : ");
			searchKeyword = rs.readLine();
			query = "Select * from MusicList where lyrics like '%"+searchKeyword+"%'";
			pstmt = DBA.con.createStatement();
			
			rt = pstmt.executeQuery(query);
			
			
			while(rt.next()) {
				int music_id = rt.getInt("music_id");
				String title = rt.getString("title");
				String artist_name = rt.getString("artist_name");
				String album_name = rt.getString("album_name");
				String release_date = rt.getString("release_date");
				String lyrics = rt.getString("lyrics");
				String genre = rt.getString("genre");
				int count = rt.getInt("count");
				
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.println("\tLyrics");
				System.out.println(lyrics);
				System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.format("music_id	     title	     artist_name                        album_name	                   release_date	             genre         count");
				System.out.println();
				System.out.format("%5s %20s %20s %50s %20s %20s %10s\n",music_id, title, artist_name, album_name, release_date, genre, count);
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.println();
			}
			break;
			
		default :
			System.out.println("Please select again!");
			break;
		
		}
	}
	
	
	
}
