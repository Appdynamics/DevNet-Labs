CREATE DATABASE supercars;

connect supercars;

CREATE TABLE MANUFACTURER (
    MANUFACTURER_ID MEDIUMINT NOT NULL,
    NAME VARCHAR(30),
    WEB VARCHAR(50),
    EMAIL VARCHAR(50),
    LOGO VARCHAR(30),
    PRIMARY KEY (MANUFACTURER_ID)
);

CREATE TABLE CARS (
     CAR_ID MEDIUMINT NOT NULL AUTO_INCREMENT,
     NAME VARCHAR(30),
     MODEL VARCHAR(30),
     DESCRIPTION VARCHAR(3000),
     MANUFACTURER_ID MEDIUMINT NOT NULL,
     COLOUR VARCHAR(20),
     YEAR MEDIUMINT,
     PRICE FLOAT,
     SUMMARY VARCHAR(3000),
     PHOTO VARCHAR(30),
     PRIMARY KEY (CAR_ID)
);

CREATE TABLE ENQUIRIES (
    ENQUIRY_ID MEDIUMINT NOT NULL AUTO_INCREMENT,
    NAME VARCHAR(50),
    EMAIL VARCHAR(50),
    COMMENT VARCHAR(200),
    CAR_ID MEDIUMINT,
    DUMMY MEDIUMINT,
    PRIMARY KEY (ENQUIRY_ID)
);

INSERT INTO MANUFACTURER (MANUFACTURER_ID, NAME, WEB, EMAIL, LOGO) VALUES
    (1, 'Porsche', 'http://www.porsche.com', 'web@porsche.com', 'Porsche.gif'),
    (2, 'Ferrari', 'http://www.ferrari.com/en_us/', 'web@ferrari.com','Ferrari.gif'),
    (3, 'Aston Martin','http://www.astonmartin.com','web@astonmartin.com','AstonMartin.gif'),
    (4, 'BMW', 'http://www.bmw.com/com/en/', 'web@bmw.com', 'Bmw.gif'),
    (5, 'Mercedes', 'https://www.mbusa.com/en/home', 'web@mbusa.com', 'Mercedes.gif'),
    (6, 'Jaguar', 'http://www.jaguarusa.com/index.html', 'web@jaguarusa.com', 'Jaguar.gif'),
    (7, 'Lamborghini', 'http://www.lamborghini.com/en/home/', 'web@lamborghini.com', 'Lamborghini.gif'),
    (8, 'Lotus', 'http://www.lotuscars.com', 'web@lotuscars.com', 'Lotus.gif'),
    (9, 'McLaren', 'https://www.mclaren.com/', 'web@mclaren.com', 'McLaren.gif');

INSERT INTO CARS (CAR_ID, NAME, MODEL, DESCRIPTION, MANUFACTURER_ID, COLOUR, YEAR, PRICE, SUMMARY, PHOTO) VALUES
    (1, 'V8', 'Vantage', '2019 Aston Martin Vantage in Jet Black over Pure Black Alcantara/Obsidian Black Leather. This Vantage is a local 1-owner trade-in that was originally sold here. Options include Black Body-Pack, Comfort Collection, Tech Collection, Exterior Black Collection, Embroidered Aston Wings Headrest, Graphite Seat Belts, and Black Cross Brace. This Vantage still shows like new with the remainder of the factory warranty.', 3, 'Jet Black', '2019', '125000', 'Mileage: 1,512 miles
 Transmission: 8-Speed Automatic
 Exterior Color: Jet Black
 Interior Color: Pure Black
 Maximum Seating: 2 seats
 Gas Mileage: 18 MPG City 25 MPG Highway
 Engine: V8
 Drivetrain: Rear-Wheel Drive
 Fuel Type: Gasoline', '36'),
    (2, 'V12', 'DB11', '2017 Aston Martin DB11 Launch Edition Cinnabar Orange RWD 8-Speed Automatic V12 Options: Power Seat Bolsters, Auto Park Assist, Brogue Detailing, Brake Calipers Black, Ventilated Front Seats, Dark Exterior Finisher Pack, Paint Contemporary, Headrest Embroidery - Aston Martin Wings, Heavy Pile Floor Mats / Leather Binding, Garage Door Opener, Headlining - Match to Seat Inner, Protective Tape, Lther Col - Out of Range/Match to Sample, Gloss Black Roof Panel, Nexus Quilting, S/Wheel - C/Keyed - Dark Chrome Bezel, Internal Shadow Pack, Trim Inlay High Gloss Chopped Carbon, 10spk Directional Gloss Black DT.',3, 'Cinnabar Orange', '2017', '162900', 'Mileage: 5,565 miles
 Transmission: 8-Speed Automatic
 Exterior Color: Cinnabar Orange
 Interior Color: Metallic Black
 Maximum Seating: 4 seats
 Gas Mileage: 15 MPG City 21 MPG Highway
 Engine: V12
 Drivetrain: Rear-Wheel Drive
 Fuel Type: Gasoline', '37');

