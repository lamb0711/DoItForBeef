package edu.handong.database.team6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Music {
	BufferedReader rs = new BufferedReader(new InputStreamReader(System.in));
	MusicList musicList = new MusicList();
	PreparedStatement pstmt = null;
	
	int menuOption = 1;
	
	void seletMenu() throws Exception{
		
		while(menuOption != 0) {
			
			System.out.println("\tNAVER MUSIC");
	        System.out.println(" ------------------------");
			System.out.println("1. Add Music ");
			System.out.println("2. Search Music Information");
			
			
			System.out.println("0 : Back to main menu ");
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
				
			case 0:
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
	
	void searchMusicInfo(){
		String searchKeyword;
		
		System.out.println("\tSelect search category");
        System.out.println(" ------------------------");
        System.out.print(" 1. Music title");
        System.out.print(" 2. Artist name");
        System.out.print(" 3. Album name");
        System.out.println(" ------------------------");
		
        
        
		
		//String query = 
	}
	
	
	
}
