USE [master]
GO
/****** Object:  Database [FlightStore]    Script Date: 19.01.2020 16:14:41 ******/
CREATE DATABASE [FlightStore]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'FlightStore', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.HUBERTSQL\MSSQL\DATA\FlightStore.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'FlightStore_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.HUBERTSQL\MSSQL\DATA\FlightStore_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [FlightStore] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [FlightStore].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [FlightStore] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [FlightStore] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [FlightStore] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [FlightStore] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [FlightStore] SET ARITHABORT OFF 
GO
ALTER DATABASE [FlightStore] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [FlightStore] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [FlightStore] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [FlightStore] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [FlightStore] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [FlightStore] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [FlightStore] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [FlightStore] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [FlightStore] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [FlightStore] SET  DISABLE_BROKER 
GO
ALTER DATABASE [FlightStore] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [FlightStore] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [FlightStore] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [FlightStore] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [FlightStore] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [FlightStore] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [FlightStore] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [FlightStore] SET RECOVERY FULL 
GO
ALTER DATABASE [FlightStore] SET  MULTI_USER 
GO
ALTER DATABASE [FlightStore] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [FlightStore] SET DB_CHAINING OFF 
GO
ALTER DATABASE [FlightStore] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [FlightStore] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [FlightStore] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'FlightStore', N'ON'
GO
ALTER DATABASE [FlightStore] SET QUERY_STORE = OFF
GO
USE [FlightStore]
GO
/****** Object:  User [Klient]    Script Date: 19.01.2020 16:14:41 ******/
CREATE USER [Klient] FOR LOGIN [Klient] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  User [Admin]    Script Date: 19.01.2020 16:14:41 ******/
CREATE USER [Admin] FOR LOGIN [Admin] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  Table [dbo].[Pasazer]    Script Date: 19.01.2020 16:14:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Pasazer](
	[PasazerID] [int] IDENTITY(1,1) NOT NULL,
	[Imie] [nvarchar](30) NOT NULL,
	[Nazwisko] [nvarchar](30) NOT NULL,
	[Email] [nvarchar](40) NOT NULL,
	[Telefon_osobisty] [nvarchar](16) NOT NULL,
	[AdresID] [int] NULL,
	[UserID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[PasazerID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Bagaz]    Script Date: 19.01.2020 16:14:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Bagaz](
	[BagazID] [int] IDENTITY(1,1) NOT NULL,
	[Pakiet] [nvarchar](30) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[BagazID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Lotnisko]    Script Date: 19.01.2020 16:14:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Lotnisko](
	[LotniskoID] [int] IDENTITY(1,1) NOT NULL,
	[Nazwa] [nvarchar](30) NOT NULL,
	[AdresID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[LotniskoID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Bilet]    Script Date: 19.01.2020 16:14:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Bilet](
	[BiletID] [int] IDENTITY(1,1) NOT NULL,
	[Numer_siedzenia] [int] NOT NULL,
	[Status] [varchar](20) NOT NULL,
	[BagazID] [int] NULL,
	[UserID] [int] NULL,
	[Numer_lotu] [int] NULL,
	[PasazerID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[BiletID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Lot]    Script Date: 19.01.2020 16:14:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Lot](
	[LotID] [int] IDENTITY(1,1) NOT NULL,
	[Data_wylotu] [date] NOT NULL,
	[Miejsce_wylotu] [int] NULL,
	[Miejsce_przylotu] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[LotID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[KupioneBilety]    Script Date: 19.01.2020 16:14:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[KupioneBilety] AS
SELECT b.BiletID, b.Numer_siedzenia, p.Imie, p.Nazwisko, p.Email, p.Telefon_osobisty, p.UserID AS Numer_Kupującego, bg.Pakiet,
l.Data_wylotu, lotnWy.Nazwa AS MiejsceWylotu, lotnPrzy.Nazwa AS MiejcePrzylotu
FROM Bilet b  JOIN
Pasazer p ON b.PasazerID = p.PasazerID JOIN
Bagaz bg ON bg.BagazID = b.BagazID JOIN
Lot l ON b.Numer_lotu = l.LotID JOIN
Lotnisko lotnWy ON lotnWy.LotniskoID = l.Miejsce_wylotu JOIN
Lotnisko lotnPrzy ON lotnPrzy.LotniskoID = l.Miejsce_przylotu;
GO
/****** Object:  Table [dbo].[Adres]    Script Date: 19.01.2020 16:14:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Adres](
	[AdresID] [int] IDENTITY(1,1) NOT NULL,
	[Kraj] [nvarchar](30) NOT NULL,
	[Wojewodztwo] [nvarchar](30) NULL,
	[Powiat] [nvarchar](30) NULL,
	[Miejscowosc] [nvarchar](30) NOT NULL,
	[Ulica] [nvarchar](30) NULL,
	[Numer_domu] [nvarchar](30) NOT NULL,
	[Numer_lokalu] [nvarchar](30) NULL,
PRIMARY KEY CLUSTERED 
(
	[AdresID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[DanePasazera]    Script Date: 19.01.2020 16:14:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE VIEW [dbo].[DanePasazera] AS
SELECT p.PasazerID, p.Imie, p.Nazwisko, p.Email, p.Telefon_osobisty, p.UserID AS Numer_Konta, a.Kraj,
a.Wojewodztwo, a.Powiat, a.Miejscowosc,a.Ulica, a.Numer_domu, a.Numer_lokalu 
FROM Pasazer p JOIN Adres a ON p.AdresID = a.AdresID;
GO
/****** Object:  View [dbo].[Loty]    Script Date: 19.01.2020 16:14:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[Loty] AS
SELECT l.LotID, l.Data_Wylotu, lotnWy.Nazwa AS Miejsce_wylotu, lotnPrzy.Nazwa AS Miejsce_przylotu
FROM Lot l JOIN Lotnisko lotnWy ON lotnWy.LotniskoID = l.Miejsce_wylotu JOIN
Lotnisko lotnPrzy ON lotnPrzy.LotniskoID = l.Miejsce_przylotu;
GO
/****** Object:  Table [dbo].[Konto]    Script Date: 19.01.2020 16:14:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Konto](
	[UserID] [int] IDENTITY(1,1) NOT NULL,
	[Login] [nvarchar](20) NOT NULL,
	[Password] [nvarchar](20) NOT NULL,
	[Admin] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[UserID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Adres] ON 

INSERT [dbo].[Adres] ([AdresID], [Kraj], [Wojewodztwo], [Powiat], [Miejscowosc], [Ulica], [Numer_domu], [Numer_lokalu]) VALUES (1, N'Polska', N'Mazowieskie', N'Warszawa', N'Warszawa', N'Konstytucji', N'24', N'2')
INSERT [dbo].[Adres] ([AdresID], [Kraj], [Wojewodztwo], [Powiat], [Miejscowosc], [Ulica], [Numer_domu], [Numer_lokalu]) VALUES (2, N'Finlandia', N'Niewazne', N'Łękołody', N'Kopnehaga', N'Sztokholmska', N'314', N'23')
INSERT [dbo].[Adres] ([AdresID], [Kraj], [Wojewodztwo], [Powiat], [Miejscowosc], [Ulica], [Numer_domu], [Numer_lokalu]) VALUES (3, N'Polska', N'Śląskie', N'Katowice', N'Katowice', N'smogowa', N'23', N'2')
INSERT [dbo].[Adres] ([AdresID], [Kraj], [Wojewodztwo], [Powiat], [Miejscowosc], [Ulica], [Numer_domu], [Numer_lokalu]) VALUES (4, N'Estonia', N'Kaczmarskie', N'Inny', N'Stolica', N'estonska', N'57', N'23')
INSERT [dbo].[Adres] ([AdresID], [Kraj], [Wojewodztwo], [Powiat], [Miejscowosc], [Ulica], [Numer_domu], [Numer_lokalu]) VALUES (7, N'Szkocja', N'inne', N'inny', N'inna', N'szkocka', N'323', N'12')
INSERT [dbo].[Adres] ([AdresID], [Kraj], [Wojewodztwo], [Powiat], [Miejscowosc], [Ulica], [Numer_domu], [Numer_lokalu]) VALUES (8, N'Polska', N'Wielkopolskie', N'Poznanski', N'Poznan', N'poznanska', N'35', N'23')
INSERT [dbo].[Adres] ([AdresID], [Kraj], [Wojewodztwo], [Powiat], [Miejscowosc], [Ulica], [Numer_domu], [Numer_lokalu]) VALUES (9, N'ZSRR', N'Kaliningrad', N'qweety', N'assfasdga', N'petersburdzka', N'99', N'9')
INSERT [dbo].[Adres] ([AdresID], [Kraj], [Wojewodztwo], [Powiat], [Miejscowosc], [Ulica], [Numer_domu], [Numer_lokalu]) VALUES (10, N'Iran', N'qwerty', N'qwerty', N'qwerty', N'bangladeszka', N'14', N'5')
INSERT [dbo].[Adres] ([AdresID], [Kraj], [Wojewodztwo], [Powiat], [Miejscowosc], [Ulica], [Numer_domu], [Numer_lokalu]) VALUES (11, N'fvvbs', N'xvxvbf', N'sdvsvs', N'vcxvx', N'fbdsb', N'23', N'12')
SET IDENTITY_INSERT [dbo].[Adres] OFF
SET IDENTITY_INSERT [dbo].[Bagaz] ON 

INSERT [dbo].[Bagaz] ([BagazID], [Pakiet]) VALUES (1, N'MAŁY')
INSERT [dbo].[Bagaz] ([BagazID], [Pakiet]) VALUES (2, N'ŚREDNI')
INSERT [dbo].[Bagaz] ([BagazID], [Pakiet]) VALUES (3, N'DUŻY')
SET IDENTITY_INSERT [dbo].[Bagaz] OFF
SET IDENTITY_INSERT [dbo].[Bilet] ON 

INSERT [dbo].[Bilet] ([BiletID], [Numer_siedzenia], [Status], [BagazID], [UserID], [Numer_lotu], [PasazerID]) VALUES (2, 1, N'ZAK', NULL, NULL, NULL, NULL)
INSERT [dbo].[Bilet] ([BiletID], [Numer_siedzenia], [Status], [BagazID], [UserID], [Numer_lotu], [PasazerID]) VALUES (7, 1, N'ZAKUPIONY', 2, 15, 5, 13)
INSERT [dbo].[Bilet] ([BiletID], [Numer_siedzenia], [Status], [BagazID], [UserID], [Numer_lotu], [PasazerID]) VALUES (8, 2, N'ANULOWANY', 1, 15, 5, 13)
INSERT [dbo].[Bilet] ([BiletID], [Numer_siedzenia], [Status], [BagazID], [UserID], [Numer_lotu], [PasazerID]) VALUES (9, 1, N'ZAKUPIONY', 1, 15, 8, 13)
INSERT [dbo].[Bilet] ([BiletID], [Numer_siedzenia], [Status], [BagazID], [UserID], [Numer_lotu], [PasazerID]) VALUES (10, 3, N'ANULOWANY', 1, 15, 5, 13)
INSERT [dbo].[Bilet] ([BiletID], [Numer_siedzenia], [Status], [BagazID], [UserID], [Numer_lotu], [PasazerID]) VALUES (11, 2, N'ZAKUPIONY', 1, 15, 8, 13)
SET IDENTITY_INSERT [dbo].[Bilet] OFF
SET IDENTITY_INSERT [dbo].[Konto] ON 

INSERT [dbo].[Konto] ([UserID], [Login], [Password], [Admin]) VALUES (2, N'Hubert', N'hubert', 1)
INSERT [dbo].[Konto] ([UserID], [Login], [Password], [Admin]) VALUES (3, N'Krzysztof', N'krzysztof', 1)
INSERT [dbo].[Konto] ([UserID], [Login], [Password], [Admin]) VALUES (4, N'Jacek', N'JACEK', 0)
INSERT [dbo].[Konto] ([UserID], [Login], [Password], [Admin]) VALUES (5, N'Andrzej', N'andrzej', 0)
INSERT [dbo].[Konto] ([UserID], [Login], [Password], [Admin]) VALUES (6, N'Robert', N'robert', 0)
INSERT [dbo].[Konto] ([UserID], [Login], [Password], [Admin]) VALUES (7, N'Alicja', N'alicja', 0)
INSERT [dbo].[Konto] ([UserID], [Login], [Password], [Admin]) VALUES (8, N'Jacek', N'JACEK', 0)
INSERT [dbo].[Konto] ([UserID], [Login], [Password], [Admin]) VALUES (11, N'Arek', N'arek', 0)
INSERT [dbo].[Konto] ([UserID], [Login], [Password], [Admin]) VALUES (13, N'Jaroslaw', N'JAROSLAW', 0)
INSERT [dbo].[Konto] ([UserID], [Login], [Password], [Admin]) VALUES (14, N'Karol', N'KAROL', 0)
INSERT [dbo].[Konto] ([UserID], [Login], [Password], [Admin]) VALUES (15, N'Antoni', N'antoni', 0)
SET IDENTITY_INSERT [dbo].[Konto] OFF
SET IDENTITY_INSERT [dbo].[Lot] ON 

INSERT [dbo].[Lot] ([LotID], [Data_wylotu], [Miejsce_wylotu], [Miejsce_przylotu]) VALUES (4, CAST(N'2020-04-14' AS Date), 2, 3)
INSERT [dbo].[Lot] ([LotID], [Data_wylotu], [Miejsce_wylotu], [Miejsce_przylotu]) VALUES (5, CAST(N'2021-04-20' AS Date), 2, 4)
INSERT [dbo].[Lot] ([LotID], [Data_wylotu], [Miejsce_wylotu], [Miejsce_przylotu]) VALUES (6, CAST(N'2012-01-12' AS Date), 4, 2)
INSERT [dbo].[Lot] ([LotID], [Data_wylotu], [Miejsce_wylotu], [Miejsce_przylotu]) VALUES (7, CAST(N'2021-04-20' AS Date), 2, 4)
INSERT [dbo].[Lot] ([LotID], [Data_wylotu], [Miejsce_wylotu], [Miejsce_przylotu]) VALUES (8, CAST(N'2021-04-20' AS Date), 3, 4)
SET IDENTITY_INSERT [dbo].[Lot] OFF
SET IDENTITY_INSERT [dbo].[Lotnisko] ON 

INSERT [dbo].[Lotnisko] ([LotniskoID], [Nazwa], [AdresID]) VALUES (1, N'Warszawa', NULL)
INSERT [dbo].[Lotnisko] ([LotniskoID], [Nazwa], [AdresID]) VALUES (2, N'Berlin', NULL)
INSERT [dbo].[Lotnisko] ([LotniskoID], [Nazwa], [AdresID]) VALUES (3, N'Londyn', NULL)
INSERT [dbo].[Lotnisko] ([LotniskoID], [Nazwa], [AdresID]) VALUES (4, N'Paryż', NULL)
SET IDENTITY_INSERT [dbo].[Lotnisko] OFF
SET IDENTITY_INSERT [dbo].[Pasazer] ON 

INSERT [dbo].[Pasazer] ([PasazerID], [Imie], [Nazwisko], [Email], [Telefon_osobisty], [AdresID], [UserID]) VALUES (2, N'Janusz', N'B', N'b@gmail.com', N'456982652', NULL, 2)
INSERT [dbo].[Pasazer] ([PasazerID], [Imie], [Nazwisko], [Email], [Telefon_osobisty], [AdresID], [UserID]) VALUES (6, N'Jacek', N'Gacek', N'jacek@02.pl', N'456325987', 4, 8)
INSERT [dbo].[Pasazer] ([PasazerID], [Imie], [Nazwisko], [Email], [Telefon_osobisty], [AdresID], [UserID]) VALUES (11, N'Jaroslaw', N'Pszenny', N'inny@o2.de', N'951753000', 9, 13)
INSERT [dbo].[Pasazer] ([PasazerID], [Imie], [Nazwisko], [Email], [Telefon_osobisty], [AdresID], [UserID]) VALUES (12, N'Karol', N'Kulawy', N'normalny@gmail.com', N'999999696', 10, 14)
INSERT [dbo].[Pasazer] ([PasazerID], [Imie], [Nazwisko], [Email], [Telefon_osobisty], [AdresID], [UserID]) VALUES (13, N'Antoni', N'asdf', N'avcx@gmail.com', N'4567531589', 11, 15)
SET IDENTITY_INSERT [dbo].[Pasazer] OFF
ALTER TABLE [dbo].[Bilet]  WITH CHECK ADD FOREIGN KEY([BagazID])
REFERENCES [dbo].[Bagaz] ([BagazID])
GO
ALTER TABLE [dbo].[Bilet]  WITH CHECK ADD FOREIGN KEY([Numer_lotu])
REFERENCES [dbo].[Lot] ([LotID])
GO
ALTER TABLE [dbo].[Bilet]  WITH CHECK ADD FOREIGN KEY([PasazerID])
REFERENCES [dbo].[Pasazer] ([PasazerID])
GO
ALTER TABLE [dbo].[Bilet]  WITH CHECK ADD FOREIGN KEY([UserID])
REFERENCES [dbo].[Konto] ([UserID])
GO
ALTER TABLE [dbo].[Lot]  WITH CHECK ADD FOREIGN KEY([Miejsce_przylotu])
REFERENCES [dbo].[Lotnisko] ([LotniskoID])
GO
ALTER TABLE [dbo].[Lot]  WITH CHECK ADD FOREIGN KEY([Miejsce_wylotu])
REFERENCES [dbo].[Lotnisko] ([LotniskoID])
GO
ALTER TABLE [dbo].[Lotnisko]  WITH CHECK ADD FOREIGN KEY([AdresID])
REFERENCES [dbo].[Adres] ([AdresID])
GO
ALTER TABLE [dbo].[Pasazer]  WITH CHECK ADD FOREIGN KEY([AdresID])
REFERENCES [dbo].[Adres] ([AdresID])
GO
ALTER TABLE [dbo].[Pasazer]  WITH CHECK ADD FOREIGN KEY([UserID])
REFERENCES [dbo].[Konto] ([UserID])
GO
USE [master]
GO
ALTER DATABASE [FlightStore] SET  READ_WRITE 
GO
