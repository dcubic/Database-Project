

CREATE TABLE Authority (Title varchar2(20) NOT NULL PRIMARY KEY);

CREATE TABLE Army(Unit  varchar2(20) NOT NULL PRIMARY KEY, Title varchar2(20), FOREIGN KEY (Title) REFERENCES Authority(Title));

CREATE TABLE Citizen(CID INTEGER PRIMARY KEY, Unit varchar2(20), ReligiousDenomination varchar2(20), Name varchar2(20), Rank INTEGER, FOREIGN KEY (Unit) REFERENCES Army(Unit));

CREATE TABLE Holiday(Name varchar2(20), AssociatedReligion varchar2(20),Start_Date DATE, PRIMARY KEY (Name, AssociatedReligion));

CREATE TABLE Celebrates (CID INTEGER, AssociatedReligion varchar2(20), Name varchar2(20), PRIMARY KEY (CID, Name, AssociatedReligion), FOREIGN KEY (CID) REFERENCES Citizen(CID) ON DELETE CASCADE, FOREIGN KEY (Name) REFERENCES Holiday(Name) ON DELETE CASCADE, FOREIGN KEY (AssociatedReligion) REFERENCES Holiday(AssociatedReligion) ON DELETE CASCADE);

CREATE TABLE PlotOfLand
(
    Location        CHAR(20),
    WorkingFunction CHAR(20),
    LandType        CHAR(20),
    Title           CHAR(20) NOT NULL,
    PRIMARY KEY (Location),
    FOREIGN KEY (Title) REFERENCES Authority
);

CREATE TABLE Structure
(
    RelativeCoordinates CHAR(20),
    Dimensions          CHAR(20),
    StructureType       CHAR(20),
    Location            CHAR(20),
    PRIMARY KEY (RelativeCoordinates, Location),
    FOREIGN KEY (Location) REFERENCES PlotOfLand ON DELETE CASCADE
);

CREATE TABLE CitizenMerchant
(
    CID      INTEGER,
    Hometown CHAR(20),
    PRIMARY KEY (CID),
    FOREIGN KEY (CID) REFERENCES Citizen ON DELETE CASCADE
);

CREATE TABLE CitizenPeasant
(
    CID     INTEGER,
    Peasant CHAR(20),
    PRIMARY KEY (CID),
    FOREIGN KEY (CID) REFERENCES Citizen ON DELETE CASCADE
);

CREATE TABLE TributePayments
(
    CID         INTEGER,
    Title       CHAR(20),
    TributeType CHAR(20),
    Amount      INTEGER,
    PRIMARY KEY (CID, Title),
    FOREIGN KEY (CID) REFERENCES Citizen ON DELETE CASCADE,
    FOREIGN KEY (Title) REFERENCES Authority ON DELETE CASCADE
);

CREATE TABLE Livestock
(
    TagID           INTEGER,
    HealthCondition CHAR(50),
    Breed           CHAR(20),
    Location        CHAR(20) NOT NULL,
    CID             INTEGER  NOT NULL,
    PRIMARY KEY (TagID),
    FOREIGN KEY (CID) REFERENCES Citizen,
    FOREIGN KEY (Location) REFERENCES PlotOfLand
);

CREATE TABLE LivestockBreed
(
    Breed   CHAR(20),
    Species CHAR(20),
    PRIMARY KEY (Breed),
    FOREIGN KEY (Breed) REFERENCES Livestock
);

CREATE TABLE Trades
(
    Citizen1CID INTEGER,
    Citizen2CID INTEGER,
    GoodsTraded CHAR(80),
    Trade_Date  DATE,
    PRIMARY KEY (Citizen1CID, Citizen2CID, Trade_Date),
    FOREIGN KEY (Citizen1CID) REFERENCES Citizen ON DELETE CASCADE,
    FOREIGN KEY (Citizen2CID) REFERENCES Citizen ON DELETE CASCADE
);

CREATE TABLE Holiday
(
    Name               CHAR(20),
    AssociatedReligion CHAR(20),
    Start_Date         DATE,
    PRIMARY KEY (Name, AssociatedReligion)
);

CREATE TABLE HolidayLeader
(
    AssociatedReligion    CHAR(20),
    HasAssociatedReligion BOOLEAN,
    PRIMARY KEY (AssociatedReligion)
);

CREATE TABLE WorksOn
(
    CID      INTEGER,
    Title    CHAR(20),
    Location CHAR(20),
    Salary   INTEGER,
    PRIMARY KEY (CID, Title, Location),
    FOREIGN KEY (CID) REFERENCES Citizen ON DELETE CASCADE,
    FOREIGN KEY (Location) REFERENCES PlotOfLand ON DELETE CASCADE,
    FOREIGN KEY (Title) REFERENCES Authority ON DELETE CASCADE,
    UNIQUE (CID)
);

CREATE TABLE Battles
(
    Army1_Unit  CHAR(20),
    Army2_Unit  CHAR(20),
    Battle_Date DATE,
    PRIMARY KEY (Army1_Unit, Army2_Unit, Battle_Date),
    FOREIGN KEY (Army1_Unit) REFERENCES Army,
    FOREIGN KEY (Army2_Unit) REFERENCES Army
);