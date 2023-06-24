-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 24 Haz 2023, 21:40:51
-- Sunucu sürümü: 10.4.28-MariaDB
-- PHP Sürümü: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `ogrencibilgisistemidb`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `bolum`
--

CREATE TABLE `bolum` (
  `bolumId` int(11) NOT NULL,
  `adi` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `bolum`
--

INSERT INTO `bolum` (`bolumId`, `adi`) VALUES
(1, 'Bilgisayar Mühendisliği'),
(2, 'Elektrik-Elektronik Mühendisliği'),
(3, 'Makine Mühendisliği'),
(4, 'Kimya Mühendisliği'),
(5, 'İnşaat Mühendisliği'),
(6, 'Endüstri Mühendisliği'),
(7, 'Gıda Mühendisliği'),
(8, 'Çevre Mühendisliği');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `ders`
--

CREATE TABLE `ders` (
  `dersId` int(11) NOT NULL,
  `bolumId` int(11) NOT NULL,
  `adi` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `ders`
--

INSERT INTO `ders` (`dersId`, `bolumId`, `adi`) VALUES
(1, 1, 'Veritabanı Yönetimi'),
(2, 1, 'Algoritma ve Veri Yapıları'),
(3, 1, 'Web Programlama'),
(4, 1, 'Bilgisayar Ağları'),
(5, 2, 'Elektrik Devreleri'),
(6, 2, 'Sinyal İşleme'),
(7, 2, 'Kontrol Sistemleri'),
(8, 2, 'Elektrik Makinaları'),
(9, 3, 'Termodinamik'),
(10, 3, 'Makine Elemanları'),
(11, 3, 'Isı Transferi'),
(12, 3, 'Üretim Teknolojileri'),
(13, 4, 'Organik Kimya'),
(14, 4, 'Fizikokimya'),
(15, 4, 'Kimyasal Prosesler'),
(16, 4, 'Analitik Kimya'),
(17, 5, 'Yapı Mekaniği'),
(18, 5, 'Betonarme'),
(19, 5, 'Geoteknik'),
(20, 5, 'Ulaştırma'),
(21, 6, 'Üretim Yönetimi'),
(22, 6, 'Kalite Kontrol'),
(23, 6, 'Endüstriyel İşletmelerde Maliyet Muhasebesi'),
(24, 6, 'Operasyon Araştırması'),
(25, 7, 'Gıda Mikrobiyolojisi'),
(26, 7, 'Gıda Analizi'),
(27, 7, 'Gıda İşleme Teknolojileri'),
(28, 7, 'Besin Kimyası'),
(29, 8, 'Atık Su Arıtma'),
(30, 8, 'Hava Kirliliği Kontrolü'),
(31, 8, 'Çevre İçin Modelleme'),
(32, 8, 'Çevre Etiği');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `login`
--

CREATE TABLE `login` (
  `kullaniciId` int(11) NOT NULL,
  `kullaniciAdi` varchar(50) NOT NULL,
  `sifre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `login`
--

INSERT INTO `login` (`kullaniciId`, `kullaniciAdi`, `sifre`) VALUES
(1, 'admin', '123');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `notlar`
--

CREATE TABLE `notlar` (
  `notlarId` int(11) NOT NULL,
  `ogrenciId` int(11) NOT NULL,
  `dersId` int(11) NOT NULL,
  `vizeNotlar` double NOT NULL,
  `finalNotlar` double NOT NULL,
  `ortNotlar` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `notlar`
--

INSERT INTO `notlar` (`notlarId`, `ogrenciId`, `dersId`, `vizeNotlar`, `finalNotlar`, `ortNotlar`) VALUES
(1, 1, 1, 55, 47, 50.2),
(4, 3, 4, 80, 90, 86),
(9, 4, 3, 45, 34, 38.4),
(14, 18, 17, 45, 70, 60),
(15, 20, 17, 70, 90, 82);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `ogrenci`
--

CREATE TABLE `ogrenci` (
  `ogrenciId` int(11) NOT NULL,
  `bolumId` int(11) NOT NULL,
  `ogrNo` varchar(50) NOT NULL,
  `adi` varchar(50) NOT NULL,
  `soyadi` varchar(50) NOT NULL,
  `tcNo` varchar(50) NOT NULL,
  `cinsiyet` varchar(1) NOT NULL,
  `dogumTarihi` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `ogrenci`
--

INSERT INTO `ogrenci` (`ogrenciId`, `bolumId`, `ogrNo`, `adi`, `soyadi`, `tcNo`, `cinsiyet`, `dogumTarihi`) VALUES
(1, 1, '201903027', 'Ahmet', 'Yılmaz', '12345678901', 'E', '2000-01-01'),
(2, 1, '201903028', 'Ayşe', 'Kara', '23456789012', 'K', '2000-02-02'),
(3, 1, '201903029', 'Mehmet', 'Demir', '34567890123', 'E', '2000-03-03'),
(4, 1, '201903030', 'Zeynep', 'Aktaş', '45678901234', 'K', '2000-04-04'),
(5, 2, '201903031', 'Emre', 'Öz', '56790123389', 'E', '2000-03-29'),
(6, 2, '201903032', 'Fatma', 'Yıldız', '67890123456', 'K', '2000-06-06'),
(7, 2, '201903033', 'Hasan', 'Koç', '78901234567', 'E', '2000-07-07'),
(8, 2, '201903034', 'Selin', 'Öztürk', '89012345678', 'K', '2000-08-08'),
(9, 3, '201903035', 'Cem', 'Aydın', '90123456789', 'E', '2000-09-09'),
(10, 3, '201903036', 'Ebru', 'Öztürk', '01234567890', 'K', '2000-10-10'),
(11, 3, '201903037', 'Gökhan', 'Kara', '12345678901', 'E', '2000-11-11'),
(12, 3, '201903038', 'İrem', 'Yıldırım', '23456789012', 'K', '2000-12-12'),
(13, 4, '201903039', 'Kadir', 'Çelik', '34567890123', 'E', '2000-01-01'),
(14, 4, '201903040', 'Elif', 'Yılmaz', '45678901234', 'K', '2000-02-02'),
(15, 4, '201903041', 'Murat', 'Aydın', '56789012345', 'E', '2000-03-03'),
(16, 4, '201903042', 'Sema', 'Demir', '67890123456', 'K', '2000-04-04'),
(17, 5, '201903043', 'Ahmet', 'Yıldız', '78901234567', 'E', '2000-05-05'),
(18, 5, '201903044', 'Ayşe', 'Kara', '89012345678', 'K', '2000-06-06'),
(19, 5, '201903045', 'Mehmet', 'Şahin', '90123456789', 'E', '2000-07-07'),
(20, 5, '201903046', 'Zeynep', 'Yılmaz', '01234567890', 'K', '2000-08-08'),
(21, 6, '201903047', 'Ali', 'Koç', '12345678901', 'E', '2000-09-09'),
(22, 6, '201903048', 'Fatma', 'Öztürk', '23456789012', 'K', '2000-10-10'),
(23, 6, '201903049', 'Hasan', 'Aydın', '34567890123', 'E', '2000-11-11'),
(24, 6, '201903050', 'Selin', 'Kara', '45678901234', 'K', '2000-12-12'),
(25, 7, '201903051', 'Cem', 'Yıldırım', '56789012345', 'E', '2000-01-01'),
(26, 7, '201903052', 'Ebru', 'Aydın', '67890123456', 'K', '2000-02-02'),
(27, 7, '201903053', 'Gökhan', 'Koç', '78901234567', 'E', '2000-03-03'),
(29, 8, '201903055', 'Kadir', 'Kara', '90123456789', 'E', '2000-05-05'),
(30, 8, '201903056', 'Elif', 'Yıldız', '01234567890', 'K', '2000-06-06'),
(31, 8, '201903057', 'Murat', 'Çelik', '12345678901', 'E', '2000-07-07'),
(32, 8, '201903058', 'Sema', 'Yılmaz', '23456789012', 'K', '2000-08-08'),
(44, 7, '201903059', 'Ahmet', 'Öz', '12345678901', 'E', '2000-01-13');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `ogretim_uyesi`
--

CREATE TABLE `ogretim_uyesi` (
  `ogretimUyesiId` int(11) NOT NULL,
  `bolumId` int(11) NOT NULL,
  `dersId` int(11) NOT NULL,
  `ogrtUyeNo` varchar(50) NOT NULL,
  `adi` varchar(50) NOT NULL,
  `soyadi` varchar(50) NOT NULL,
  `dogumTarihi` date NOT NULL,
  `cinsiyet` varchar(1) NOT NULL,
  `tcNo` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `ogretim_uyesi`
--

INSERT INTO `ogretim_uyesi` (`ogretimUyesiId`, `bolumId`, `dersId`, `ogrtUyeNo`, `adi`, `soyadi`, `dogumTarihi`, `cinsiyet`, `tcNo`) VALUES
(1, 1, 1, 'OGRT001', 'Ahmet', 'Yılmaz', '1980-01-01', 'E', '12345678901'),
(2, 1, 2, 'OGRT002', 'Ayşe', 'Demir', '1985-02-15', 'K', '23456789012'),
(3, 1, 3, 'OGRT003', 'Mehmet', 'Kara', '1978-05-10', 'E', '34567890123'),
(4, 1, 4, 'OGRT004', 'Zeynep', 'Aktaş', '1982-09-20', 'K', '45678901234'),
(5, 2, 5, 'OGRT005', 'Mustafa', 'Şahin', '1975-03-03', 'E', '56789012345'),
(6, 2, 6, 'OGRT006', 'Gizem', 'Yıldız', '1987-07-07', 'K', '67890123456'),
(7, 2, 7, 'OGRT007', 'Emre', 'Özdemir', '1984-11-11', 'E', '78901234567'),
(8, 2, 8, 'OGRT008', 'Fatma', 'Şahin', '1981-04-25', 'K', '12308973231'),
(9, 3, 9, 'OGRT009', 'Fatih', 'Yıldırım', '1977-06-20', 'E', '90123456789'),
(10, 3, 10, 'OGRT010', 'Elif', 'Aydın', '1983-08-18', 'K', '01234567890'),
(11, 3, 11, 'OGRT011', 'Ali', 'Öztürk', '1979-12-05', 'E', '12345678901'),
(12, 3, 12, 'OGRT012', 'Sevgi', 'Kara', '1986-02-28', 'K', '23456789012'),
(13, 4, 13, 'OGRT013', 'Cem', 'Demir', '1976-09-10', 'E', '34567890123'),
(14, 4, 14, 'OGRT014', 'Aylin', 'Kara', '1982-01-24', 'K', '45678901234'),
(15, 4, 15, 'OGRT015', 'Can', 'Koç', '1978-04-12', 'E', '56789012345'),
(16, 4, 16, 'OGRT016', 'Zeynep', 'Aksoy', '1984-07-07', 'K', '67890123456'),
(17, 5, 17, 'OGRT017', 'Murat', 'Şahin', '1979-02-14', 'E', '78901234567'),
(18, 5, 18, 'OGRT018', 'Elif', 'Yıldız', '1985-05-30', 'K', '89012345678'),
(19, 5, 19, 'OGRT019', 'Emre', 'Koçak', '1981-08-08', 'E', '90123456789'),
(20, 5, 20, 'OGRT020', 'Selin', 'Öztürk', '1987-11-26', 'K', '01234567890'),
(21, 6, 21, 'OGRT021', 'Mehmet', 'Aydın', '1978-03-02', 'E', '12345678901'),
(22, 6, 22, 'OGRT022', 'Sema', 'Öztürk', '1984-06-17', 'K', '23456789012'),
(23, 6, 23, 'OGRT023', 'Ahmet', 'Demir', '1980-09-23', 'E', '34567890123'),
(24, 6, 24, 'OGRT024', 'Kübra', 'Er', '2023-06-07', 'K', '87851490210'),
(25, 7, 25, 'OGRT025', 'Kemal', 'Kara', '1977-05-07', 'E', '56789012345'),
(26, 7, 26, 'OGRT026', 'Esra', 'Aktaş', '1983-08-21', 'K', '67890123456'),
(27, 7, 27, 'OGRT027', 'Mehmet', 'Şahin', '1979-11-14', 'E', '78901234567'),
(28, 7, 28, 'OGRT028', 'Zeynep', 'Yıldırım', '1985-02-28', 'K', '89012345678'),
(29, 8, 29, 'OGRT029', 'Mustafa', 'Öztürk', '1976-08-09', 'E', '90123456789'),
(30, 8, 30, 'OGRT030', 'Gizem', 'Koçak', '1982-11-23', 'K', '01234567890'),
(31, 8, 31, 'OGRT031', 'Emre', 'Yılmaz', '1978-02-17', 'E', '12345678901'),
(32, 8, 32, 'OGRT032', 'Selin', 'Aydın', '1984-05-03', 'K', '23456789012');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `bolum`
--
ALTER TABLE `bolum`
  ADD PRIMARY KEY (`bolumId`);

--
-- Tablo için indeksler `ders`
--
ALTER TABLE `ders`
  ADD PRIMARY KEY (`dersId`);

--
-- Tablo için indeksler `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`kullaniciId`);

--
-- Tablo için indeksler `notlar`
--
ALTER TABLE `notlar`
  ADD PRIMARY KEY (`notlarId`);

--
-- Tablo için indeksler `ogrenci`
--
ALTER TABLE `ogrenci`
  ADD PRIMARY KEY (`ogrenciId`);

--
-- Tablo için indeksler `ogretim_uyesi`
--
ALTER TABLE `ogretim_uyesi`
  ADD PRIMARY KEY (`ogretimUyesiId`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `bolum`
--
ALTER TABLE `bolum`
  MODIFY `bolumId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- Tablo için AUTO_INCREMENT değeri `ders`
--
ALTER TABLE `ders`
  MODIFY `dersId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- Tablo için AUTO_INCREMENT değeri `login`
--
ALTER TABLE `login`
  MODIFY `kullaniciId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Tablo için AUTO_INCREMENT değeri `notlar`
--
ALTER TABLE `notlar`
  MODIFY `notlarId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Tablo için AUTO_INCREMENT değeri `ogrenci`
--
ALTER TABLE `ogrenci`
  MODIFY `ogrenciId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- Tablo için AUTO_INCREMENT değeri `ogretim_uyesi`
--
ALTER TABLE `ogretim_uyesi`
  MODIFY `ogretimUyesiId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
