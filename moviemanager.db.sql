BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS `User` (
	`id`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`username`	TEXT,
	`password`	TEXT,
	`role`	INTEGER
);
CREATE TABLE IF NOT EXISTS `RatingUsia` (
	`id`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`Field2`	INTEGER
);
CREATE TABLE IF NOT EXISTS `Movie` (
	`id`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`judul`	TEXT,
	`tahun`	INTEGER,
	`genre`	INTEGER,
	`durasi`	INTEGER,
	`sutradara`	INTEGER,
	`penulis`	TEXT,
	`produser`	TEXT,
	`rating_usia`	INTEGER,
	`deskripsi`	TEXT,
	`gambar`	TEXT,
	`trailer`	TEXT,
	`star`	INTEGER,
	`review`	TEXT,
	FOREIGN KEY(`genre`) REFERENCES `Genre`(`id`),
	FOREIGN KEY(`rating_usia`) REFERENCES `RatingUsia`(`id`)
);
CREATE TABLE IF NOT EXISTS `Genre` (
	`id`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`Field2`	INTEGER
);
COMMIT;
