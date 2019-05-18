# DoItForBee
scenario
USER(4)
1. log in
2. sign in
3. change password
3-1. user search
4. withdrawal(IDEA)
5.

Mail(8/29)
1. Write mail in TempMailBox(done)
2. Send to user(done)
3. check mailsbox (done) -marking, delete, move to spam mail, pass
4. check tome mailbox(done) -marking, delete, move to spam mail
5. check trash can(done) -marking, delete, move to spam mail
6. check spam mail box(done) -marking, delete, move to spam mail
7. check sent mail box(done) -marking, delete, move to spam mail
8. check temporary mail box(done) -marking, delete, move to spam mail
9. classify mails to mail Box, to-me mail Box, spam mail box
10. add my mail box
11. modify my mail box name
12. delete my mail box name
13. delete specific mail by sender name
14. check marked mail
15. mark mail as important
16. register spam id
17. search in mail by sender
18. search in mail by date
19. move specific mail to trash can
20. pass mail
21. change mail status
22. modify specific mail in to-me Mail Box
23. search in mail by contents
24. check unread mail
25. check read mail
26. sort by sender
27. sort by date
28. sort by title
29. move specific mail to spam mail

Music(/18)
1. Top 10
2. Print All Music List
1. Search Music title
 2. Search Artist
  3. Search Album
  4. Search Genre
  5. Search Lyrics
4. Listen Music
1. Listen Music - Add Music to MyList
2. Listen Music - Add Music to Favorite List
5. Ask new music
6. Ask to modify wrong information (3과 같음)
7. Make new MyList
8. Print MyList Music
9. Print Recent Music
10. Pirnt My Favorite
11. Check My Voucher
12. Pirnt Purchased Music

Table
create table user(user_id INT, user_name VARCHAR(15), user_pw INT, user_phone INT, user_email VARCHAR(40));
create table Mailbox(mailID INT, sender INT, title VARCHAR(30), contents VARCHAR(300));
create table ToMeMailbox(mailID INT, title VARCHAR(30), contents VARCHAR(300));
create table SpamMailBox(mailID INT, sender INT,  title VARCHAR(30), contents VARCHAR(300));
create table MyMailBox(mailID INT, sender INT,  title VARCHAR(30), contents VARCHAR(300), mailBoxName VARCHAR(30));
create table TrashCan(mailID INT, sender INT,  title VARCHAR(30), contents VARCHAR(300));
create table temporarymailbox(mailID MEDIUMINT NOT NULL AUTO_INCREMENT,
                               sender INT,
                               receiver INT,
                               title VARCHAR(30),
                               contets VARCHAR(300),
                               PRIMARY KEY(mailID));
create table SentMailBox(mailID INT, sender INT,  title VARCHAR(30), contents VARCHAR(300));
create table AllMailBox(mailID INT, sender INT,  title VARCHAR(30), contents VARCHAR(300), mailBoxName VARCHAR(30));

##MUSIC

CREATE TABLE MusicList( music_id INT(11) NOT NULL AUTO_INCREMENT, title VARCHAR(100) NULL, artist_name VARCHAR(100) NULL, album_name VARCHAR(100) NULL, release_date DATETIME NOT NULL, lyrics TEXT NULL, PRIMARY KEY(music_id), title VARCHAR(100) NULL, count INT(100) NOT NULL, genre varchar(100));

create table MusicUser( id INT(11) NOT NULL, myList TEXT NULL, count INT(11) NOT NULL, PRIMARY KEY(id),Voucher varchar(100));

create table FavoriteList( music_id INT(11) NOT NULL, id INT(11) NOT NULL);

create table MyList( id INT(11) NOT NULL, music_id INT(11) NOT NULL, myList_name varchar(100));

create table RecentMusic( id INT(11) NOT NULL, music_id INT(11) NOT NULL, count int(11) NOT NULL);
