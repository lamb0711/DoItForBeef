package edu.handong.database.team6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
	
	void seletMenu() throws Exception{
		
		while(menuOption != 0) {
			
			System.out.println("\tNAVER MUSIC");
	        System.out.println(" ------------------------");
	        System.out.println("01. Top 10"); 
	        System.out.println("02. Print All Music List");
			System.out.println("03. Search Music  (Music title | Artist | Album | Genre | Lyrics)"); //장르 추가~!
	        System.out.println("04. Listen Music");//최근 추가, 횟수 ++
	        System.out.println("    - Add Music to MyList");
	        System.out.println("    - Add Music to Favorite List");
	        System.out.println("05. Ask new music "); //장르 추가~~
	        System.out.println("06. Ask to modify wrong information (Music title | Artist | Album | Genre | Lyrics)");
	        System.out.println();
	        System.out.println("\tMY MUSIC");
	        System.out.println(" ------------------------");
	        System.out.println("07. Make new MyList");
	        System.out.println("08. Print MyList Music");
	        System.out.println("09. Print Recent Music");
	        System.out.println("10. Pirnt My Favorite");
	        System.out.println("11. Check My Voucher");
	        System.out.println("12. Pirnt Purchased Music");
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
				
				break;
				
			case 2:
				pirntAllMusicList();
				break;
				
			case 3:
				searchMusicInfo();
				break;
				
			case 4:
				
				break;
				
			case 5:
				addMusic();
				break;
				
				
			default :
				System.out.println("Please select again!");
				break;
			
			}
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
