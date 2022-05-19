USE soundcloud_gr_6_sec1;

-- Amount of money donated to each track. 
SELECT t.TRACK_NAME AS "track name", SUM(udtt.AMOUNT) AS "donation amount"
FROM UserDonateToTrack udtt
INNER JOIN Track t ON udtt.TRACK_ID = t.TRACK_ID
GROUP BY t.TRACK_NAME;

-- Count number of donations occurs for each track
SELECT t.TRACK_NAME AS "track name", COUNT(udtt.AMOUNT) AS "donation count"
FROM UserDonateToTrack udtt
INNER JOIN Track t ON udtt.TRACK_ID = t.TRACK_ID
GROUP BY t.TRACK_NAME;

-- count number of follower for each user and show name
SELECT u.USER_ID AS "UserID", u.NAME AS "UserName", COUNT(ufu.USER_ID2) AS "follower"
FROM UserFollowUser ufu
INNER JOIN User u ON ufu.USER_ID1 = u.USER_ID
GROUP BY u.USER_ID;

-- count number of comment on each track, sort by comment count greater to lower
SELECT ucot.TRACK_ID, t.TRACK_NAME, COUNT(ucot.USER_ID) AS "comment count"
FROM UserCommentOnTrack ucot
INNER JOIN Track t ON ucot.TRACK_ID = t.TRACK_ID
GROUP BY ucot.TRACK_ID
ORDER BY COUNT(ucot.USER_ID) DESC;

-- count number of track released by each user
SELECT u.USER_ID AS "UserId", u.NAME AS "Username", COUNT(TRACK_ID) AS "number of track own"
FROM User u
LEFT JOIN Track t ON u.USER_ID = t.USER_ID
GROUP BY u.USER_ID
ORDER BY COUNT(TRACK_ID) DESC;

-- count number of track in album
SELECT tsa.ALBUM_ID, a.ALBUM_NAME, COUNT(tsa.TRACK_ID) AS "number of track in album"
FROM TrackStoreAlbum tsa
INNER JOIN Album a ON tsa.ALBUM_ID = a.ALBUM_ID
GROUP BY ALBUM_ID;

-- count listener for each track
SELECT th.TRACK_ID, t.TRACK_NAME, COUNT(th.USER_ID) AS "listener count"
FROM TrackHistory th
RIGHT JOIN Track t ON th.TRACK_ID = t.TRACK_ID
GROUP BY th.TRACK_ID
ORDER BY COUNT(th.USER_ID) DESC;

-- count view for every track for each user
SELECT u.NAME, COUNT(th.TRACK_ID) AS "listener count"
FROM User u
LEFT JOIN Track t ON u.USER_ID = t.USER_ID
LEFT JOIN TrackHistory th ON t.TRACK_ID = th.TRACK_ID 
GROUP BY u.USER_ID 
ORDER BY COUNT(th.TRACK_ID) DESC;

-- count view for each track with user name
SELECT u.NAME AS "UserName", t.TRACK_NAME AS "Track name", COUNT(th.TRACK_ID) AS "listener count"
FROM User u
LEFT JOIN Track t ON u.USER_ID = t.USER_ID
LEFT JOIN TrackHistory th ON t.TRACK_ID = th.TRACK_ID 
GROUP BY u.USER_ID, t.TRACK_NAME
ORDER BY COUNT(th.TRACK_ID) DESC;

-- count track that never be in post
SELECT COUNT(*) AS "number of track that never be in post"
FROM Track t
WHERE t.TRACK_ID NOT IN (
	SELECT DISTINCT TRACK_ID 
    FROM TrackInPost
);

-- Count station's follower, sort by follower
SELECT s.STATION_NAME, COUNT(ufs.USER_ID) AS "follower"
FROM UserFollowStation ufs
RIGHT JOIN Station s ON ufs.STATION_ID = s.STATION_ID 
GROUP BY ufs.STATION_ID
ORDER BY COUNT(ufs.USER_ID) DESC;

-- count repost for each user when its has been repost by other (not oneself), sort by repost count desc
SELECT u.NAME, COUNT(urt.REPOST_ID) AS "repost count"
FROM UserRepostTrack urt
RIGHT OUTER JOIN User u ON urt.USER_ID = u.USER_ID
LEFT OUTER JOIN Track t ON urt.TRACK_ID = t.TRACK_ID
WHERE (u.USER_ID != t.USER_ID) 
	OR (urt.USER_ID IS NULL)
GROUP BY u.USER_ID
ORDER BY COUNT(urt.REPOST_ID) DESC;

-- number of people who use gmail
SELECT COUNT(*) AS "number of people who use gmail"
FROM User
WHERE USER_EMAIL LIKE "%@gmail.com";

-- find most block user
SELECT ubu.USER_ID2 AS "UserID", u.NAME AS "UserName", COUNT(ubu.USER_ID1) AS "Blocked by"
FROM userblockuser ubu
INNER JOIN User u ON ubu.USER_ID2 = u.USER_ID
GROUP BY ubu.USER_ID2
ORDER BY COUNT(ubu.USER_ID1) DESC
LIMIT 1;

-- number of track that has been play since  "2021/08/01 0:00:00"
SELECT COUNT(*) AS "track played"
FROM TrackHistory th
WHERE th.TIMESTAMP > "2021/08/01 0:00:00";

-- count number of license type used
SELECT LICENSE_TYPE AS "License type", COUNT(TRACK_ID) AS "License count"
FROM License
GROUP BY LICENSE_TYPE;

-- count operator called for each month
SELECT YEAR(TIMESTAMP_AT_CALL) AS "year", MONTH(TIMESTAMP_AT_CALL) AS "month", COUNT(*) AS "operator called count"
FROM UserCallOperator
GROUP BY YEAR(TIMESTAMP_AT_CALL), MONTH(TIMESTAMP_AT_CALL)
ORDER BY TIMESTAMP_AT_CALL;

-- average, std, min, max, variance of string length
SELECT ROUND(AVG(CHAR_LENGTH(MESSAGE)), 2) AS "average string length", 
	ROUND(STD(CHAR_LENGTH(MESSAGE)), 2) AS "std string length", 
    MIN(CHAR_LENGTH(MESSAGE)) AS "min string length", 
    MAX(CHAR_LENGTH(MESSAGE)) AS "max string length",
    VARIANCE(CHAR_LENGTH(MESSAGE)) AS "var string lenght"
FROM UserMessageUser;


