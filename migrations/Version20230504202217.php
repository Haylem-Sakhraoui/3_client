<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20230504202217 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE commentaire CHANGE date_c date_c DATETIME NOT NULL');
        $this->addSql('ALTER TABLE competences ADD user_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE competences ADD CONSTRAINT FK_DB2077CEA76ED395 FOREIGN KEY (user_id) REFERENCES user (id)');
        $this->addSql('CREATE INDEX IDX_DB2077CEA76ED395 ON competences (user_id)');
        $this->addSql('ALTER TABLE reclamation CHANGE description description VARCHAR(140) NOT NULL');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE commentaire CHANGE date_c date_c DATE NOT NULL');
        $this->addSql('ALTER TABLE competences DROP FOREIGN KEY FK_DB2077CEA76ED395');
        $this->addSql('DROP INDEX IDX_DB2077CEA76ED395 ON competences');
        $this->addSql('ALTER TABLE competences DROP user_id');
        $this->addSql('ALTER TABLE reclamation CHANGE description description VARCHAR(255) NOT NULL');
    }
}
