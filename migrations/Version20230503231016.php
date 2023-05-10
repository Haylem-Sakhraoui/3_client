<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20230503231016 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE password-reset (email VARCHAR(30) NOT NULL, token VARCHAR(60) NOT NULL, created_at VARCHAR(30) NOT NULL, PRIMARY KEY(email)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE personne (id INT AUTO_INCREMENT NOT NULL, nom VARCHAR(255) NOT NULL, prenom VARCHAR(255) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE poste (id_poste INT AUTO_INCREMENT NOT NULL, id_candidat INT DEFAULT NULL, id_demande INT DEFAULT NULL, experience INT NOT NULL, description VARCHAR(200) NOT NULL, INDEX poste_ibfk_1 (id_demande), INDEX fk_user_poste (id_candidat), PRIMARY KEY(id_poste)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE services (id_service INT AUTO_INCREMENT NOT NULL, nom_service VARCHAR(20) NOT NULL, nb_tot_freelance INT NOT NULL, PRIMARY KEY(id_service)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE user (id INT AUTO_INCREMENT NOT NULL, username VARCHAR(30) NOT NULL, email VARCHAR(30) NOT NULL, password VARCHAR(60) NOT NULL, role VARCHAR(255) NOT NULL, remember_token VARCHAR(60) NOT NULL, created_at VARCHAR(30) NOT NULL, updated_at VARCHAR(30) DEFAULT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE poste ADD CONSTRAINT FK_7C890FAB3A6E58E4 FOREIGN KEY (id_candidat) REFERENCES user (id)');
        $this->addSql('ALTER TABLE poste ADD CONSTRAINT FK_7C890FABF8097ED5 FOREIGN KEY (id_demande) REFERENCES demande (id_demande)');
        $this->addSql('DROP TABLE cv');
        $this->addSql('ALTER TABLE categories ADD CONSTRAINT FK_3AF346683F0033A2 FOREIGN KEY (id_service) REFERENCES services (id_service)');
        $this->addSql('ALTER TABLE demande ADD CONSTRAINT FK_2694D7A5ABE6DBAB FOREIGN KEY (id_recruteur) REFERENCES user (id)');
        $this->addSql('ALTER TABLE scolarite CHANGE nom_etablissement nom_etablissement VARCHAR(100) NOT NULL, CHANGE date_obtention date_obtention DATE NOT NULL');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE categories DROP FOREIGN KEY FK_3AF346683F0033A2');
        $this->addSql('ALTER TABLE demande DROP FOREIGN KEY FK_2694D7A5ABE6DBAB');
        $this->addSql('CREATE TABLE cv (id INT AUTO_INCREMENT NOT NULL, filename VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, filetype VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, filesize INT NOT NULL, data VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_general_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('ALTER TABLE poste DROP FOREIGN KEY FK_7C890FAB3A6E58E4');
        $this->addSql('ALTER TABLE poste DROP FOREIGN KEY FK_7C890FABF8097ED5');
        $this->addSql('DROP TABLE password-reset');
        $this->addSql('DROP TABLE personne');
        $this->addSql('DROP TABLE poste');
        $this->addSql('DROP TABLE services');
        $this->addSql('DROP TABLE user');
        $this->addSql('ALTER TABLE scolarite CHANGE nom_etablissement nom_etablissement VARCHAR(11) NOT NULL, CHANGE date_obtention date_obtention DATETIME NOT NULL');
    }
}
