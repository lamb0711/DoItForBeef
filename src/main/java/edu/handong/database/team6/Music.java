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
	        System.out.println("0. Top 10");
			System.out.println("1. Add Music ");
			System.out.println("2. Search Music Information");
	        System.out.println("3. Listen Music");//최근 추가, favorite 체크 가능, MyList 추가 가능, 횟수 ++
	        System.out.println("    Add Music to MyList");
	        System.out.println("    Add Music to Favorite List");
	        System.out.println("4. Ask for correction of wrong information");
	        System.out.println("5. Print All Music List");
	        System.out.println();
	        System.out.println("\tMY MUSIC");
	        System.out.println(" ------------------------");
	        System.out.println("6. Print MyList Music");
	        System.out.println("7. Print Recent Music");
	        System.out.println("8. Pirnt My Favorite");
	        System.out.println("9. Check My Voucher");
	        System.out.println("10. Pirnt Purchased Music");
	        System.out.println(" ------------------------");
			System.out.println("00 : Back to main menu ");
			System.out.println(" ------------------------");
			System.out.println(" Select the Menu : ");
			
			
			menuOption = Integer.parseInt(rs.readLine());
			
			switch(menuOption) {
			case 1:
				addMusic();
				break;
				
			case 2:
				searchMusicInfo();
				break;
				
			case 3:
				searchMusicInfo();
				break;
				
			case 4:
				searchMusicInfo();
				break;
				
			
			case 00:
				break;
				
			default :
				System.out.println("Please select again!");
				break;
			
			}
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
			line = rs.readLine();
		}
		
		musicList.setLyrics(lyrics);
		
		
		String query = "insert into MusicList(artist_name, album_name, release_date, lyrics, title) values(?,?,?,?,?);";
		
		pstmt = DBA.con.prepareStatement(query);
		
		pstmt.setString(1,musicList.getArtist_name());
		pstmt.setString(2,musicList.getAlbum_name());
		pstmt.setString(3,musicList.getRelease_date());
		pstmt.setString(4,musicList.getLyrics());
		pstmt.setString(5,musicList.getTitle());
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
        System.out.println(" ------------------------");
		
        menuOption = Integer.parseInt(rs.readLine());
        
        switch(menuOption) {
        
		case 1:
			System.out.print("Please input Music title : ");
			searchKeyword = rs.readLine();
			query = "Select * from MusicList where title like '%"+searchKeyword+"%'";
			pstmt = DBA.con.createStatement();
			
			rt = pstmt.executeQuery(query);
			
			System.out.format("music_id			title			artist_name			album_name			release_date			lyrics");
			System.out.println();
			while(rt.next()) {
				int music_id = rt.getInt("music_id");
				String title = rt.getString("title");
				String artist_name = rt.getString("artist_name");
				String album_name = rt.getString("album_name");
				String release_date = rt.getString("release_date");
				String lyrics = rt.getString("lyrics");
				
				System.out.format("%5s %30s %30s %30s %30s %30s\n",music_id, title, artist_name, album_name, release_date, lyrics);
				System.out.println();
			}
			
			break;
			
		case 2:
			System.out.print("Please input Artist name of Music : ");
			searchKeyword = rs.readLine();
			query = "Select * from MusicList where artist_name like '%"+searchKeyword+"%'";
			pstmt = DBA.con.createStatement();
			
			rt = pstmt.executeQuery(query);
			
			System.out.format("music_id			title			artist_name			album_name			release_date			lyrics");
			System.out.println();
			while(rt.next()) {
				int music_id = rt.getInt("music_id");
				String title = rt.getString("title");
				String artist_name = rt.getString("artist_name");
				String album_name = rt.getString("album_name");
				String release_date = rt.getString("release_date");
				String lyrics = rt.getString("lyrics");
				
				System.out.format("%5s %30s %30s %30s %30s %s\n",music_id, title, artist_name, album_name, release_date, lyrics);
				System.out.println();
			}
			
			
			break;
			
		case 3:
			System.out.print("Please input Album name of Music : ");
			searchKeyword = rs.readLine();
			query = "Select * from MusicList where album_name like '%"+searchKeyword+"%'";
			pstmt = DBA.con.createStatement();
			
			rt = pstmt.executeQuery(query);
			
			System.out.format("music_id			title			artist_name			album_name			release_date			lyrics");
			System.out.println();
			while(rt.next()) {
				int music_id = rt.getInt("music_id");
				String title = rt.getString("title");
				String artist_name = rt.getString("artist_name");
				String album_name = rt.getString("album_name");
				String release_date = rt.getString("release_date");
				String lyrics = rt.getString("lyrics");
				
				System.out.format("%5s %30s %30s %30s %30s %30s\n",music_id, title, artist_name, album_name, release_date, lyrics);
				System.out.println();
			}
			break;
			
		default :
			System.out.println("Please select again!");
			break;
		
		}
        
		
		//String query = 
	}
	
	
	
}
