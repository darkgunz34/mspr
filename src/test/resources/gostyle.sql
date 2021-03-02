-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mer. 03 fév. 2021 à 08:27
-- Version du serveur :  5.7.31
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `gostyle`
--

-- --------------------------------------------------------

--
-- Structure de la table `coupon`
--

DROP TABLE IF EXISTS `coupon`;
CREATE TABLE IF NOT EXISTS `coupon` (
  `id_coupon` int(11) NOT NULL AUTO_INCREMENT,
  `nom_coupon` varchar(255) DEFAULT NULL,
  `motif_coupon` varchar(255) DEFAULT NULL,
  `code_coupon` varchar(255) DEFAULT NULL,
  `date_validation_coupon` varchar(255) DEFAULT NULL,
  `valide_coupon` tinyint(1) DEFAULT NULL,
  `id_user` mediumtext,
  `id_partenaire` mediumtext,
  PRIMARY KEY (`id_coupon`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `coupon`
--

INSERT INTO `coupon` (`id_coupon`, `nom_coupon`, `motif_coupon`, `code_coupon`, `date_validation_coupon`, `valide_coupon`, `id_user`, `id_partenaire`) VALUES
(1, 'coupon 1', 'reduction', 'AB7784', '15 février 2021', 1, '1', '1'),
(2, 'coupon 2', 'reduction', 'AB7784', '15 février 2021', 1, '2', '2'),
(3, 'coupon 3', 'reduction', 'AB7784', '15 février 2021', 1, '3', '3'),
(4, 'coupon 4', 'reduction', 'AB7784', '15 janvier 2021', 0, '1', '2'),
(5, 'coupon 5', 'reduction', 'AB7784', '15 décembre 2020', 0, '2', '3');

-- --------------------------------------------------------

--
-- Structure de la table `partenaire`
--

DROP TABLE IF EXISTS `partenaire`;
CREATE TABLE IF NOT EXISTS `partenaire` (
  `id_partenaire` int(11) NOT NULL AUTO_INCREMENT,
  `nom_partenaire` varchar(255) DEFAULT NULL,
  `adresse_partenaire` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_partenaire`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `partenaire`
--

INSERT INTO `partenaire` (`id_partenaire`, `nom_partenaire`, `adresse_partenaire`) VALUES
(1, 'adidas', 'rue de l\'adidas'),
(2, 'nike', 'rue de la nike'),
(3, 'reebok', 'rue de reebok');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `pseudo_user` varchar(255) DEFAULT NULL,
  `password_user` varchar(255) DEFAULT NULL,
  `mail_user` varchar(255) DEFAULT NULL,
  `nom_user` varchar(255) DEFAULT NULL,
  `prenom_user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id_user`, `pseudo_user`, `password_user`, `mail_user`, `nom_user`, `prenom_user`) VALUES
(1, 'user1', '24c9e15e52afc47c225b757e7bee1f9d', 'user1@user.fr', 'user1_nom', 'user1_prenom'),
(2, 'user2', '7e58d63b60197ceb55a1c487989a3720', 'user2@user.fr', 'user2_nom', 'user2_prenom'),
(3, 'user3', '92877af70a45fd6a2ed7fe81e1236b78', 'user3@user.fr', 'user3_nom', 'user3_prenom');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
