-- phpMyAdmin SQL Dump
-- version 4.2.12deb2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 16, 2015 at 03:41 PM
-- Server version: 5.5.44-0+deb8u1
-- PHP Version: 5.6.12-0+deb8u1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `asae`
--

-- --------------------------------------------------------

--
-- Table structure for table `ASISTENCIA`
--

CREATE TABLE IF NOT EXISTS `ASISTENCIA` (
`ASIID` bigint(20) NOT NULL,
  `ASIFECHA` datetime NOT NULL,
  `ASIOBSERVACIONES` varchar(400) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ASISTENCIA`
--

INSERT INTO `ASISTENCIA` (`ASIID`, `ASIFECHA`, `ASIOBSERVACIONES`) VALUES
(1, '2015-08-08 00:00:00', ''),
(2, '2015-10-02 00:00:00', '');

-- --------------------------------------------------------

--
-- Table structure for table `CARGO`
--

CREATE TABLE IF NOT EXISTS `CARGO` (
`CARID` bigint(20) NOT NULL,
  `CARNOMBRE` varchar(75) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `CARGO`
--

INSERT INTO `CARGO` (`CARID`, `CARNOMBRE`) VALUES
(1, 'Docente'),
(2, 'Administrativo');

-- --------------------------------------------------------

--
-- Table structure for table `DETALLEASISTENCIA`
--

CREATE TABLE IF NOT EXISTS `DETALLEASISTENCIA` (
  `ASIID` bigint(20) NOT NULL,
  `USUID` bigint(20) NOT NULL,
  `DETASISTIO` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `DETALLEASISTENCIA`
--

INSERT INTO `DETALLEASISTENCIA` (`ASIID`, `USUID`, `DETASISTIO`) VALUES
(1, 20141238, 1),
(1, 20141239, 0),
(2, 20141238, 0),
(2, 20141239, 1),
(2, 20141240, 0);

-- --------------------------------------------------------

--
-- Table structure for table `DETALLEINSCRIPCION`
--

CREATE TABLE IF NOT EXISTS `DETALLEINSCRIPCION` (
  `USUID` bigint(20) NOT NULL,
  `INSID` bigint(20) NOT NULL,
  `DETACTIVO` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `DETALLEINSCRIPCION`
--

INSERT INTO `DETALLEINSCRIPCION` (`USUID`, `INSID`, `DETACTIVO`) VALUES
(20141238, 1, 1),
(20141238, 2, 1),
(20141239, 1, 1),
(20141239, 2, 1),
(20141240, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `EVENTO`
--

CREATE TABLE IF NOT EXISTS `EVENTO` (
`EVEID` bigint(20) NOT NULL,
  `EVETITULO` varchar(500) NOT NULL,
  `EVEFECHAPUBLICACION` datetime DEFAULT NULL,
  `EVEFECHAEVENTO` datetime NOT NULL,
  `EVELUGAR` varchar(250) NOT NULL,
  `EVECONTENIDO` varchar(10000) NOT NULL,
  `EVEIMAGEN` varchar(350) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `EVENTO`
--

INSERT INTO `EVENTO` (`EVEID`, `EVETITULO`, `EVEFECHAPUBLICACION`, `EVEFECHAEVENTO`, `EVELUGAR`, `EVECONTENIDO`, `EVEIMAGEN`) VALUES
(10, 'Desafi­os y lineamientos', '2015-05-12 08:12:03', '2015-05-19 00:00:00', 'Salón Fundadores', 'En alianza con el Ministerio de la Justicia y del Derecho, la Facultad de Derecho y Ciencias Políticas y Sociales de la Universidad del Cauca, realizara, el foro regional Desafi­os y lineamientos en la formación del abogado en Colombia. Unicauca', 'url_imagen'),
(11, 'Jornada de bienestar y salud en Silvia Cauca', '2015-05-12 08:16:45', '2015-05-16 00:00:00', 'Escuela municipio de Silvia', 'La División de Salud Integral lleva a cabo una jornada de bienestar y salud dirigida a los estudiantes del programa de Regionalización en el municipio de Silvia programada para el próximo sabado 16 de mayo, a partir de las 8:00 a.m.', 'url_imagen'),
(12, 'II Seminario Internacional Diseño Vida Cotidiana', '2015-05-12 08:21:19', '2015-05-14 00:00:00', ' Paraninfo', 'La Universidad del Cauca, el Centro de Educación Continua, Abierta y Virtual CECAV, ofrecen el III Seminario Internacional de Diseño Vida Cotidiana. Nuevas Fronteras Del Diseño: Crosmedia Y Transmedia. Unicauca', 'url_imagen'),
(13, 'Tecnoinnovación IV Versión', '2015-05-12 10:26:07', '2015-05-14 00:00:00', 'Auditorio FCCEA', 'El programa de Administración de Empresas y el Grupo de Investigación en Gestión de la Tecnología y la Calidad, GTC, invitan a la comunidad universitaria y ciudadanía en general a la cuarta versión de Tecnoinnovación.', 'url_imagen'),
(14, 'II Congreso Iberoamericano de Filosofía Práctica', '2015-05-13 09:40:57', '2015-05-22 00:00:00', 'Salón Fundadores', 'La Facultad de Derecho, Ciencias Sociales y Políticas de la Universidad del Cauca, extiende invitación al II Congreso Iberoamericano de Filosofía Práctica “Justicia Social y Política en Iberoamérica”', 'url_imagen'),
(17, 'Homenaje a escritores afrocaucanos', '2015-05-13 11:03:59', '2015-05-19 00:00:00', 'Paraninfo', 'La Rectoría y la Vicerrectoría de Cultura y Bienestar, invitan a la comunidad universitaria y ciudadanía en general al acto de ‘Homenaje a escritores afrocaucanos’ en donde exaltarán la obra de Pedro Hernando González Sevillano y Baudilio Revelo Hurtado, oriundos del municipio de Guapi.', 'url_imagen'),
(18, 'Día Internacional del profesional de Enfermería', '2015-05-14 12:37:35', '2015-05-20 00:00:00', '1229 (Salón Fundadores)', 'Con el fin de conmemorar el Día Internacional del Profesional de Enfermería, el departamento de Enfermería de la Facultad de Ciencias de la Salud, llevará a cabo en el Auditorio Antonio José Lemus Guzmán una actividad académica que concluye con una jornada deportiva, lúdica y cultural.', 'url_imagen'),
(29, 'carrera', '2015-05-25 15:43:19', '2015-05-12 06:19:00', 'tulcan', 'alfjfkfj', '4C1J74BLW30GM4V.jpg'),
(30, 'carrera', '2015-05-25 15:44:32', '2015-05-12 06:19:00', 'tulcan', 'alfjfkfj', '4C1J74BLW30GM4V.jpg'),
(31, 'carrera', '2015-05-25 15:46:53', '2015-05-21 00:00:00', 'cdu', 'todas las categorias', 'WJN98QHAMAUL55C.jpg'),
(32, 'competencia de atletismo', '2015-05-25 15:53:31', '2015-05-11 00:00:00', 'cdu tulcan', 'para todas las categorias', 'DKHWF7M5F150Q86.jpg'),
(33, 'Carrera Atletica', '2015-05-25 16:34:02', '2015-05-27 00:00:00', 'CDU', 'Esta es una prueba de eventos', 'PKQ4XMK7NF9QPMU.jpg'),
(34, 'Evento nuevo', '2015-05-25 16:47:01', '2015-05-29 00:00:00', 'CDU', 'prueba dos para eventos', '3DUBXX5ZYRAHQVR.jpg'),
(35, 'Eveto nuevo2', '2015-05-25 17:12:01', '2015-05-27 00:00:00', 'CDU', 'evento 2 de prueba', 'X6AJKO08D8KSC7O.jpg'),
(36, 'Eveto nuevo3', '2015-05-25 17:24:32', '2015-05-27 00:00:00', 'CDU', 'evento 2 de prueba blala', 'CC1CYLCF2GBVSDJ.jpg'),
(37, 'Eveto nuevo4', '2015-05-25 17:29:36', '2015-05-27 00:00:00', 'CDU', 'evento 2 de prueba blala', 'OB19WBW5PATOQYE.jpg'),
(38, 'competencia de natacion', '2015-05-25 17:31:52', '2015-05-20 00:25:00', 'piscina', 'djlkjasdldj', 'N6I5DU2S68WUM9E.jpg'),
(39, 'competencia de atletismo', '2015-05-25 17:48:03', '2015-05-07 07:00:00', 'tulcan', 'asjlkjadslkj', 'IXNO1I1EY6MO529.jpg'),
(40, 'eventico', '2015-05-25 17:48:49', '2015-05-27 00:00:00', 'CDU', 'este es un evento', 'JMSM80618AF7KA5.jpg'),
(41, 'eventico', '2015-05-25 17:51:00', '2015-05-27 00:00:00', 'CDU', 'este es un evento', 'JMSM80618AF7KA5.jpg'),
(42, 'eventico', '2015-05-25 17:51:20', '2015-05-27 00:00:00', 'CDU', 'este es un evento', 'JMSM80618AF7KA5.jpg'),
(43, 'Evento', '2015-05-25 19:24:20', '2015-05-05 06:19:00', 'Aqui', 'Este es un evento', 'SIT6XQM09BBDOUI.jpg'),
(44, 'ultimo', '2015-05-25 19:35:10', '2015-05-13 00:00:00', 'cdu', 'akdjdlkdjadlk', 'WYVL236BJN8380H.jpg'),
(45, 'Evento Nuevo', '2015-05-26 21:09:19', '2015-05-10 00:00:00', 'CDU', 'Contenido del evento', '0O1KFP2WTAU4VVS.jpg'),
(46, 'evento competencia nueva actual', '2015-05-29 13:39:54', '2015-05-12 00:00:00', 'tulcan', 'Descripcion acerca del evento que se realizara en tulcan y con la ayuda de patrocinadores ', 'AHB4BGI4RCN5Z39.jpg'),
(47, 'evento competencia nueva actual', '2015-05-29 13:44:38', '2015-05-19 07:40:00', 'cdu', 'Competencia atletica en todas las modalidades', 'default.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `GRUPO`
--

CREATE TABLE IF NOT EXISTS `GRUPO` (
  `GRUID` varchar(20) NOT NULL,
  `GRUDESCRIPCION` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `GRUPO`
--

INSERT INTO `GRUPO` (`GRUID`, `GRUDESCRIPCION`) VALUES
('admin', 'administrador'),
('user', 'usuario');

-- --------------------------------------------------------

--
-- Table structure for table `HORARIO`
--

CREATE TABLE IF NOT EXISTS `HORARIO` (
`HORID` int(11) NOT NULL,
  `HORNOMBRE` varchar(100) DEFAULT NULL,
  `HORCONTENIDO` varchar(10000) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `HORARIO`
--

INSERT INTO `HORARIO` (`HORID`, `HORNOMBRE`, `HORCONTENIDO`) VALUES
(7, 'Horario Hora Saludable', '<br><table align="center" border="4" bordercolor="#22419a" cellpadding="10" cellspacing="20">\r\n<tbody><tr style="color:#ffffff" bgcolor="#22419a">\r\n<th width="80px">HORA</th><th width="80px">LUNES</th><th width="80px">MARTES</th><th width="80px">MIERCOLES</th> <th width="80px">JUEVES</th><th width="80px">VIERNES</th>\r\n</tr>\r\n<tr>  <td>8:00 a.m.</td> <td></td> <td></td> <td></td> <td></td> <td></td>  </tr>\r\n<tr>  <td>9:00 a.m.</td> <td></td> <td></td> <td></td> <td></td> <td></td>  </tr>\r\n<tr>  <td>10:00 a.m.</td> <td></td> <td></td> <td></td> <td></td> <td></td>  </tr>\r\n<tr>  <td>11:00 a.m.</td> <td></td> <td></td> <td></td> <td></td> <td>Grupo C</td>   </tr>\r\n<tr>  <td>12:00 p.m.</td> <td></td> <td></td> <td></td> <td></td> <td>Grupo C</td>   </tr>\r\n<tr>  <td>1:00 p.m.</td> <td></td> <td></td> <td></td> <td></td> <td>Grupo C</td>   </tr>\r\n<tr>  <td>2:00 p.m.</td> <td></td> <td></td> <td></td> <td></td> <td></td>   </tr>\r\n<tr>  <td>3:00 p.m.</td> <td></td> <td></td> <td></td> <td></td> <td></td>   </tr>\r\n<tr>  <td>4:00 p.m.</td> <td></td> <td></td> <td></td> <td></td> <td></td>   </tr>\r\n<tr>  <td>5:00 p.m.</td> <td></td> <td>Grupo A</td> <td>Grupo B</td> <td>Grupo A</td> <td>Grupo B</td>   </tr>\r\n<tr>  <td>6:00 p.m.</td> <td></td> <td>Grupo A</td> <td>Grupo B</td> <td>Grupo A</td> <td>Grupo B</td>   </tr>\r\n<tr>  <td>7:00 p.m.</td> <td></td> <td>Grupo A</td> <td>Grupo B</td> <td>Grupo A</td> <td>Grupo B</td>   </tr>\r\n<tr>  <td>8:00 p.m.</td> <td></td> <td>Grupo A</td> <td>Grupo B</td> <td>Grupo A</td> <td>Grupo B</td>   </tr>\r\n</tbody></table>');

-- --------------------------------------------------------

--
-- Table structure for table `INSCRIPCION`
--

CREATE TABLE IF NOT EXISTS `INSCRIPCION` (
`INSID` bigint(20) NOT NULL,
  `INSMES` varchar(20) NOT NULL,
  `INSANIO` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `INSCRIPCION`
--

INSERT INTO `INSCRIPCION` (`INSID`, `INSMES`, `INSANIO`) VALUES
(1, '8', 2015),
(2, '10', 2015);

-- --------------------------------------------------------

--
-- Table structure for table `MEDIDA`
--

CREATE TABLE IF NOT EXISTS `MEDIDA` (
`MEDID` bigint(20) NOT NULL,
  `USUID` bigint(20) NOT NULL,
  `MEDFECHA` datetime NOT NULL,
  `MEDOBSERVACIONES` varchar(400) DEFAULT NULL,
  `MEDPESO` float NOT NULL,
  `MEDTALLA` float NOT NULL,
  `MEDDEPORTE` varchar(30) DEFAULT NULL,
  `MEDTRICEPS` float NOT NULL,
  `MEDSUBESCAPULAR` float NOT NULL,
  `MEDSUPRAILIACO` float NOT NULL,
  `MEDABDOMINAL` float NOT NULL,
  `MEDMUSLO` float NOT NULL,
  `MEDPANTORILLA` float NOT NULL,
  `MEDPERIMETROMUNECA` float NOT NULL,
  `MEDPERIMETROCABEZA` float NOT NULL,
  `MEDDIAMETROBIACROMIAL` float NOT NULL,
  `MEDDIAMETROBILTIOCRISTAL` float NOT NULL,
  `MEDDIAMETROHUMERO` float NOT NULL,
  `MEDDIAMETROFEMUR` float NOT NULL,
  `MEDPERIMETROBRAZO` float NOT NULL,
  `MEDDIAMETROANTEBRAZO` float NOT NULL,
  `MEDPERIMETROPANTORRILLA` float NOT NULL,
  `MEDPERIMETROCAJATORAXICA` float NOT NULL,
  `MEDPERIMETROMUSLO` float NOT NULL,
  `MEDPULSO0` float NOT NULL,
  `MEDPULSO1` float NOT NULL,
  `MEDPULSO2` float NOT NULL,
  `MEDFLEXIBILIDAD` float NOT NULL,
  `MEDEMBERGADURA` float NOT NULL,
  `MEDSALTOMAXIMO` float NOT NULL,
  `MEDSALTOREAL` float NOT NULL,
  `MEDFCARDIACA1` int(11) DEFAULT NULL,
  `MEDFCARDIACA2` int(11) DEFAULT NULL,
  `MEDFCARDIACA3` int(11) DEFAULT NULL,
  `MEDFCARDIACA4` int(11) DEFAULT NULL,
  `MEDFCARDIACA5` int(11) DEFAULT NULL,
  `MEDFCARDIACA6` int(11) DEFAULT NULL,
  `MEDFCARDIACA7` int(11) DEFAULT NULL,
  `MEDFCARDIACA8` int(11) DEFAULT NULL,
  `MEDFCARDIACA9` int(11) DEFAULT NULL,
  `MEDFCARDIACA10` int(11) DEFAULT NULL,
  `MEDFCARDIACA11` int(11) DEFAULT NULL,
  `MEDFCARDIACA12` int(11) DEFAULT NULL,
  `MEDFCARDIACA13` int(11) DEFAULT NULL,
  `MEDFCARDIACA14` int(11) DEFAULT NULL,
  `MEDFCARDIACA15` int(11) DEFAULT NULL,
  `MEDFCARDIACA16` int(11) DEFAULT NULL,
  `MEDFCARDIACAMAXIMA` int(11) DEFAULT NULL,
  `MEDFCARDIACAREPOSO` int(11) DEFAULT NULL,
  `MEDFCARDIACAMAXIMALEGER` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `MEDIDA`
--

INSERT INTO `MEDIDA` (`MEDID`, `USUID`, `MEDFECHA`, `MEDOBSERVACIONES`, `MEDPESO`, `MEDTALLA`, `MEDDEPORTE`, `MEDTRICEPS`, `MEDSUBESCAPULAR`, `MEDSUPRAILIACO`, `MEDABDOMINAL`, `MEDMUSLO`, `MEDPANTORILLA`, `MEDPERIMETROMUNECA`, `MEDPERIMETROCABEZA`, `MEDDIAMETROBIACROMIAL`, `MEDDIAMETROBILTIOCRISTAL`, `MEDDIAMETROHUMERO`, `MEDDIAMETROFEMUR`, `MEDPERIMETROBRAZO`, `MEDDIAMETROANTEBRAZO`, `MEDPERIMETROPANTORRILLA`, `MEDPERIMETROCAJATORAXICA`, `MEDPERIMETROMUSLO`, `MEDPULSO0`, `MEDPULSO1`, `MEDPULSO2`, `MEDFLEXIBILIDAD`, `MEDEMBERGADURA`, `MEDSALTOMAXIMO`, `MEDSALTOREAL`, `MEDFCARDIACA1`, `MEDFCARDIACA2`, `MEDFCARDIACA3`, `MEDFCARDIACA4`, `MEDFCARDIACA5`, `MEDFCARDIACA6`, `MEDFCARDIACA7`, `MEDFCARDIACA8`, `MEDFCARDIACA9`, `MEDFCARDIACA10`, `MEDFCARDIACA11`, `MEDFCARDIACA12`, `MEDFCARDIACA13`, `MEDFCARDIACA14`, `MEDFCARDIACA15`, `MEDFCARDIACA16`, `MEDFCARDIACAMAXIMA`, `MEDFCARDIACAREPOSO`, `MEDFCARDIACAMAXIMALEGER`) VALUES
(1, 20141239, '2015-09-29 00:00:00', '', 1, 1, 'Atletismo', 2, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 22, 33, 43, 53, 34, 23, 123, 39, 80, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 171, NULL, NULL),
(2, 20141239, '2015-10-01 00:00:00', NULL, 0, 0, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(4, 20141239, '2015-10-01 00:00:00', NULL, 0, 0, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(5, 20141240, '2015-10-01 00:00:00', '', 80, 183, 'Ninguno', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(6, 20141240, '2015-10-02 00:00:00', '', 0, 0, 'Ninguno', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 181, 23, 123),
(7, 20141240, '2015-10-01 00:00:00', '', 0, 0, 'Ninguno', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(8, 20141240, '2015-10-02 00:00:00', NULL, 0, 0, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `NOTICIA`
--

CREATE TABLE IF NOT EXISTS `NOTICIA` (
`NOTID` bigint(20) NOT NULL,
  `NOTTITULO` varchar(500) NOT NULL,
  `NOTFECHAPUBLICACION` datetime NOT NULL,
  `NOTFECHAEDICION` datetime DEFAULT NULL,
  `NOTVISIBLE` tinyint(1) NOT NULL,
  `NOTCONTENIDO` varchar(10000) NOT NULL,
  `NOTIMAGEN` varchar(350) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `NOTICIA`
--

INSERT INTO `NOTICIA` (`NOTID`, `NOTTITULO`, `NOTFECHAPUBLICACION`, `NOTFECHAEDICION`, `NOTVISIBLE`, `NOTCONTENIDO`, `NOTIMAGEN`) VALUES
(3, 'Esto es una noticia', '2015-05-25 17:51:40', '2015-05-25 17:51:40', 1, 'Esta noticia rimeFaces is a popular open source framework for JavaServer Faces featuring over 100 components, touch optimized mobilekit, push framework, client side validation, theme engine and more.', 'default.jpg'),
(13, 'Desde aqui', '2015-05-30 07:01:36', '2015-05-30 07:01:36', 1, 'Hasta aca', '8CMTDE605A419W8.jpg'),
(14, 'Inscripciones abiertas para programas de posgrados', '2015-05-30 11:44:05', '2015-05-30 11:44:05', 1, 'La Universidad del Cauca, consciente de su compromiso con el país y la región frente a los procesos de mejoramiento de la calidad de la educación, y atendiendo aspectos importantes como la formación en valores, la formación para el trabajo y la productividad, el desarrollo del pensamiento y la generación y apropiación de ciencia y tecnología, ofrece programas de posgrado para el II Período Académico del año 2015', '0YW1PZWHD1WU5IE.jpg'),
(15, 'Nueva', '2015-10-08 17:34:03', '2015-10-08 17:34:03', 1, 'Nueva noticiia', '3I4XZDLZ4OB9SNV.jpg'),
(16, 'ff', '2015-10-08 17:39:38', '2015-10-08 17:39:38', 1, 'fff', 'BGKLRSJ0TNT6DGF.jpg'),
(17, 'noticia mage', '2015-10-09 12:02:03', '2015-10-09 12:02:03', 1, 'asdfdf', '4BK2USDWXQGBFCV.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `RECUPERARCONTRASENA`
--

CREATE TABLE IF NOT EXISTS `RECUPERARCONTRASENA` (
  `REID` bigint(20) NOT NULL,
  `REIDCIFRADO` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `TIPOUNIDADACADEMICA`
--

CREATE TABLE IF NOT EXISTS `TIPOUNIDADACADEMICA` (
`TIPID` bigint(20) NOT NULL,
  `TIPNOMBRE` varchar(75) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `TIPOUNIDADACADEMICA`
--

INSERT INTO `TIPOUNIDADACADEMICA` (`TIPID`, `TIPNOMBRE`) VALUES
(1, 'Facultad'),
(2, 'Dependencias'),
(3, 'Directivos'),
(4, 'Centro');

-- --------------------------------------------------------

--
-- Table structure for table `UNIDADACADEMICA`
--

CREATE TABLE IF NOT EXISTS `UNIDADACADEMICA` (
`UNIID` bigint(20) NOT NULL,
  `TIPID` bigint(20) NOT NULL,
  `UNINOMBRE` varchar(75) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `UNIDADACADEMICA`
--

INSERT INTO `UNIDADACADEMICA` (`UNIID`, `TIPID`, `UNINOMBRE`) VALUES
(1, 1, 'Facultad de Ingeniería Electrónica y Telecomunicaciones'),
(2, 1, 'Facultad de Ingeniería Civil'),
(3, 1, 'Facultad de Derecho, Ciencias Políticas y Sociales'),
(4, 1, 'Facultad de Ciencias Naturales, Exactas y de la Educación'),
(5, 1, 'Facultad de Ciencias Humanas y Sociales'),
(6, 1, 'Facultad de Ciencias Contables, Económicas y Administrativas'),
(7, 1, 'Facultad de Ciencias de la Salud'),
(8, 1, 'Facultad de Ciencias Agrarias'),
(9, 1, 'Facultad de Artes'),
(10, 2, 'Área de Adquisiciones e Inventarios'),
(11, 2, 'Área de Planta Física'),
(12, 2, 'Área de Egresados'),
(13, 2, 'Área de Mantenimiento'),
(14, 2, 'Área de Desarrollo Editorial'),
(15, 2, 'Área de Salud Ocupacional'),
(16, 2, 'Área de Transporte y Aseo'),
(17, 2, 'División Administrativa y de Servicios'),
(18, 2, 'División de Admisiones, Registro y Control Académico'),
(19, 2, 'División de Comunicaciones y de Prensa'),
(20, 2, 'División de Cultura y Patrimonio'),
(21, 2, 'División de Gestión de la Investigación'),
(22, 2, 'División de Gestión de Medios y Recursos Bibliográficos'),
(23, 2, 'División de Gestión del Talento Humano'),
(24, 2, 'División de Gestión Financiera'),
(25, 2, 'División de Recreación y Deporte'),
(26, 2, 'División de Salud Integral'),
(27, 2, 'División de Tecnologías de la Información y las Comunicaciones'),
(28, 2, 'Oficina de Control Interno'),
(29, 2, 'Oficina de Planeación y Desarrollo Institucional'),
(30, 2, 'Oficina de Relaciones Interinstitucionales e Internacionales'),
(31, 2, 'Oficina Jurídica'),
(32, 2, 'Programa de Formación en Idiomas (PFI)'),
(33, 2, 'Secretaría General'),
(34, 2, 'Unidad de Salud'),
(35, 3, 'Consejo Superior'),
(36, 3, 'Consejo Académico'),
(37, 3, 'Rectoría'),
(38, 3, 'Vicerrectoría Administrativa'),
(39, 3, 'Vicerrectoría de Investigaciones'),
(40, 3, 'Vicerrectoría de Cultura y Bienestar'),
(41, 3, 'Vicerrectoría Académica'),
(42, 4, 'Centro de Educación Continua'),
(43, 4, 'Centro de Escritura'),
(44, 4, 'Centro de Posgrados'),
(45, 4, 'Centro de Regionalización');

-- --------------------------------------------------------

--
-- Table structure for table `USUARIO`
--

CREATE TABLE IF NOT EXISTS `USUARIO` (
`USUID` bigint(20) NOT NULL,
  `CARID` bigint(20) DEFAULT NULL,
  `CONYUGEID` bigint(20) DEFAULT NULL,
  `UNIID` bigint(20) DEFAULT NULL,
  `USUIDENTIFICACION` bigint(20) NOT NULL,
  `USUFECHANACIMIENTO` datetime NOT NULL,
  `USUNOMBRES` varchar(75) NOT NULL,
  `USUAPELLIDOS` varchar(75) NOT NULL,
  `USUGENERO` char(1) NOT NULL,
  `USUNOMBREUSUARIO` varchar(75) NOT NULL,
  `USUCONTRASENA` varchar(250) NOT NULL,
  `USUEMAIL` varchar(150) NOT NULL,
  `USUTELEFONO` bigint(20) DEFAULT NULL,
  `USUFOTO` varchar(150) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=20141243 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `USUARIO`
--

INSERT INTO `USUARIO` (`USUID`, `CARID`, `CONYUGEID`, `UNIID`, `USUIDENTIFICACION`, `USUFECHANACIMIENTO`, `USUNOMBRES`, `USUAPELLIDOS`, `USUGENERO`, `USUNOMBREUSUARIO`, `USUCONTRASENA`, `USUEMAIL`, `USUTELEFONO`, `USUFOTO`) VALUES
(20141238, NULL, NULL, NULL, 123456789, '1973-01-01 00:00:00', 'Nairo', 'Burbano', 'M', 'admin', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'nairoburbano@unicauca.edu.co', 8204578, '123456789_1.jpg'),
(20141239, 1, NULL, 1, 76303617, '1966-08-28 00:00:00', 'Pablo Augusto', 'Mage Imbachi', 'M', 'pmage', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'pmage@unicauca.edu.co', 82354555, '76303617_2.jpeg'),
(20141240, 1, NULL, 1, 98393281, '1976-04-08 00:00:00', 'Wilson Libardo', 'Pantoja Yepez', 'M', 'wpantoja', '3c5d8b4c5e43346b9cd10b990c547f100122417d8119188c077d1b4230dccf70', 'wpantoja@gmail.com', NULL, 'vacio.jpg'),
(20141241, NULL, NULL, 1, 123, '1998-10-01 00:00:00', 'Juan', 'Perez', 'M', 'juanito', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'juanito@unicauca.edu.co', NULL, 'vacio.jpg'),
(20141242, 1, NULL, 1, 123456, '1996-10-01 00:00:00', 'Maria', 'Sanchez', 'F', 'maria', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'maria@gmail.com', NULL, '123456_1.jpeg');

-- --------------------------------------------------------

--
-- Table structure for table `USUARIOGRUPO`
--

CREATE TABLE IF NOT EXISTS `USUARIOGRUPO` (
  `GRUID` varchar(20) NOT NULL,
  `USUID` bigint(20) NOT NULL,
  `USUNOMBREUSUARIO` varchar(75) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `USUARIOGRUPO`
--

INSERT INTO `USUARIOGRUPO` (`GRUID`, `USUID`, `USUNOMBREUSUARIO`) VALUES
('admin', 20141238, 'admin'),
('user', 20141239, 'pmage'),
('user', 20141240, 'wpantoja'),
('user', 20141241, 'juanito'),
('user', 20141242, 'maria');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ASISTENCIA`
--
ALTER TABLE `ASISTENCIA`
 ADD PRIMARY KEY (`ASIID`);

--
-- Indexes for table `CARGO`
--
ALTER TABLE `CARGO`
 ADD PRIMARY KEY (`CARID`);

--
-- Indexes for table `DETALLEASISTENCIA`
--
ALTER TABLE `DETALLEASISTENCIA`
 ADD PRIMARY KEY (`ASIID`,`USUID`), ADD KEY `FK_DETALLEASISTENCIA2` (`USUID`);

--
-- Indexes for table `DETALLEINSCRIPCION`
--
ALTER TABLE `DETALLEINSCRIPCION`
 ADD PRIMARY KEY (`USUID`,`INSID`), ADD KEY `FK_DETALLEINSCRIPCION2` (`INSID`);

--
-- Indexes for table `EVENTO`
--
ALTER TABLE `EVENTO`
 ADD PRIMARY KEY (`EVEID`);

--
-- Indexes for table `GRUPO`
--
ALTER TABLE `GRUPO`
 ADD PRIMARY KEY (`GRUID`);

--
-- Indexes for table `HORARIO`
--
ALTER TABLE `HORARIO`
 ADD PRIMARY KEY (`HORID`);

--
-- Indexes for table `INSCRIPCION`
--
ALTER TABLE `INSCRIPCION`
 ADD PRIMARY KEY (`INSID`);

--
-- Indexes for table `MEDIDA`
--
ALTER TABLE `MEDIDA`
 ADD PRIMARY KEY (`MEDID`), ADD KEY `FK_USUARIOMEDIDA` (`USUID`);

--
-- Indexes for table `NOTICIA`
--
ALTER TABLE `NOTICIA`
 ADD PRIMARY KEY (`NOTID`);

--
-- Indexes for table `RECUPERARCONTRASENA`
--
ALTER TABLE `RECUPERARCONTRASENA`
 ADD PRIMARY KEY (`REID`);

--
-- Indexes for table `TIPOUNIDADACADEMICA`
--
ALTER TABLE `TIPOUNIDADACADEMICA`
 ADD PRIMARY KEY (`TIPID`);

--
-- Indexes for table `UNIDADACADEMICA`
--
ALTER TABLE `UNIDADACADEMICA`
 ADD PRIMARY KEY (`UNIID`), ADD KEY `FK_TIPOUNIDAD` (`TIPID`);

--
-- Indexes for table `USUARIO`
--
ALTER TABLE `USUARIO`
 ADD PRIMARY KEY (`USUID`), ADD KEY `FK_CONYUGE` (`CONYUGEID`), ADD KEY `FK_USUARIOCARGO` (`CARID`), ADD KEY `FK_USUARIOUNIDADACADEMICA` (`UNIID`);

--
-- Indexes for table `USUARIOGRUPO`
--
ALTER TABLE `USUARIOGRUPO`
 ADD PRIMARY KEY (`GRUID`,`USUID`), ADD KEY `FK_USUARIO_USUID` (`USUID`), ADD KEY `FK_GRUPOS_GRUID` (`GRUID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `ASISTENCIA`
--
ALTER TABLE `ASISTENCIA`
MODIFY `ASIID` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `CARGO`
--
ALTER TABLE `CARGO`
MODIFY `CARID` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `EVENTO`
--
ALTER TABLE `EVENTO`
MODIFY `EVEID` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=48;
--
-- AUTO_INCREMENT for table `HORARIO`
--
ALTER TABLE `HORARIO`
MODIFY `HORID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `INSCRIPCION`
--
ALTER TABLE `INSCRIPCION`
MODIFY `INSID` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `MEDIDA`
--
ALTER TABLE `MEDIDA`
MODIFY `MEDID` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `NOTICIA`
--
ALTER TABLE `NOTICIA`
MODIFY `NOTID` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT for table `TIPOUNIDADACADEMICA`
--
ALTER TABLE `TIPOUNIDADACADEMICA`
MODIFY `TIPID` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `UNIDADACADEMICA`
--
ALTER TABLE `UNIDADACADEMICA`
MODIFY `UNIID` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=46;
--
-- AUTO_INCREMENT for table `USUARIO`
--
ALTER TABLE `USUARIO`
MODIFY `USUID` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=20141243;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `DETALLEASISTENCIA`
--
ALTER TABLE `DETALLEASISTENCIA`
ADD CONSTRAINT `FK_DETALLEASISTENCIA` FOREIGN KEY (`ASIID`) REFERENCES `ASISTENCIA` (`ASIID`),
ADD CONSTRAINT `FK_DETALLEASISTENCIA2` FOREIGN KEY (`USUID`) REFERENCES `USUARIO` (`USUID`);

--
-- Constraints for table `DETALLEINSCRIPCION`
--
ALTER TABLE `DETALLEINSCRIPCION`
ADD CONSTRAINT `FK_DETALLEINSCRIPCION` FOREIGN KEY (`USUID`) REFERENCES `USUARIO` (`USUID`),
ADD CONSTRAINT `FK_DETALLEINSCRIPCION2` FOREIGN KEY (`INSID`) REFERENCES `INSCRIPCION` (`INSID`);

--
-- Constraints for table `MEDIDA`
--
ALTER TABLE `MEDIDA`
ADD CONSTRAINT `FK_USUARIOMEDIDA` FOREIGN KEY (`USUID`) REFERENCES `USUARIO` (`USUID`);

--
-- Constraints for table `UNIDADACADEMICA`
--
ALTER TABLE `UNIDADACADEMICA`
ADD CONSTRAINT `FK_TIPOUNIDAD` FOREIGN KEY (`TIPID`) REFERENCES `TIPOUNIDADACADEMICA` (`TIPID`);

--
-- Constraints for table `USUARIO`
--
ALTER TABLE `USUARIO`
ADD CONSTRAINT `FK_CONYUGE` FOREIGN KEY (`CONYUGEID`) REFERENCES `USUARIO` (`USUID`),
ADD CONSTRAINT `FK_USUARIOCARGO` FOREIGN KEY (`CARID`) REFERENCES `CARGO` (`CARID`),
ADD CONSTRAINT `FK_USUARIOUNIDADACADEMICA` FOREIGN KEY (`UNIID`) REFERENCES `UNIDADACADEMICA` (`UNIID`);

--
-- Constraints for table `USUARIOGRUPO`
--
ALTER TABLE `USUARIOGRUPO`
ADD CONSTRAINT `FK_GRUPOS_GRUID` FOREIGN KEY (`GRUID`) REFERENCES `GRUPO` (`GRUID`),
ADD CONSTRAINT `FK_USUARIO_USUID` FOREIGN KEY (`USUID`) REFERENCES `USUARIO` (`USUID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
