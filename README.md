# DoItForBee
scenario
USER(4)
1. log in
2. sign in
3. change password
3-1. user search
4. withdrawal(IDEA)
5.


Mail(39/50)
1. $Write mail in TempMailBox(done1)
2. $Send to user(done2)
3. $check allmailbox (done3) -select (done4), reply(done5), pass(done6), (un)mark(done7), delete(done8), spam(done 34)$
4. $check mailbox (done29) -select (done13), reply(done12), pass(done11), (un)mark(done10), delete(done9), spam(done 35)$
5. $check tome mailbox(done30) -select (done14), pass(done15), (un)mark(done16), delete(done17), spam(done 36)$
6. $check trash can(done31) -select (done18), delete(done19), clean(39)$
7. check spam mail box(done32) -select (done21), delete(done20)$
8. $check sent mail box(done33) -select (done22), pass(done23), (un)mark(done24), delete(done25)$
9. $check temporary mail box(done26) -select (done27), delete(done28), modify(37), send(38)$

------39------
10. delete specific mail by sender name
11. check marked mail(after star)
12. register spam id(additional)
13. search in mail by sender(additional)
14. search in mail by date(after develop relation)
15. change mail status(alter table [tablename] add status INT)
16. search in mail by contents(additional)
17. check unread mail (after status)
18. check read mail (after status)
19. sort by sender
20. sort by date
21. sort by title

Table
*Star default 0
1.create table user(user_id INT, user_name VARCHAR(15), user_pw INT, user_phone INT, user_email VARCHAR(40));
2.create table Mailbox(mailID INT, sender INT, title VARCHAR(30), contents VARCHAR(300));
3.create table ToMeMailbox(mailID INT, title VARCHAR(30), contents VARCHAR(300));
4.create table SpamMailBox(mailID INT, sender INT,  title VARCHAR(30), contents VARCHAR(300));
5.create table MyMailBox(mailID INT, sender INT,  title VARCHAR(30), contents VARCHAR(300), mailBoxName VARCHAR(30));
6.create table TrashCan(mailID INT, sender INT,  title VARCHAR(30), contents VARCHAR(300));
7.create table temporarymailbox(mailID MEDIUMINT NOT NULL AUTO_INCREMENT,

                               sender INT,
                               receiver INT,
                               title VARCHAR(30),
                               contets VARCHAR(300),
                               PRIMARY KEY(mailID));

8. create table SentMailBox(mailID INT, sender INT,  title VARCHAR(30), contents VARCHAR(300));
9. create table AllMailBox(mailID INT, sender INT,  title VARCHAR(30), contents VARCHAR(300), mailBoxName VARCHAR(30));


## MUSIC

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

#### Table
CREATE TABLE MusicList( music_id INT(11) NOT NULL AUTO_INCREMENT, title VARCHAR(100) NULL, artist_name VARCHAR(100) NULL, album_name VARCHAR(100) NULL, release_date DATETIME NOT NULL, lyrics TEXT NULL, PRIMARY KEY(music_id), title VARCHAR(100) NULL, count INT(100) NOT NULL, genre varchar(100));

create table MusicUser( id INT(11) NOT NULL, myList TEXT NULL, count INT(11) NOT NULL, PRIMARY KEY(id),Voucher varchar(100));

create table FavoriteList( music_id INT(11) NOT NULL, id INT(11) NOT NULL);

create table MyList( id INT(11) NOT NULL, music_id INT(11) NOT NULL, myList_name varchar(100));

create table RecentMusic( id INT(11) NOT NULL, music_id INT(11) NOT NULL, count int(11) NOT NULL);

