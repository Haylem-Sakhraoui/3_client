<?php

namespace App\Entity;

use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;
use App\Repository\ScolariteRepository;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * @ORM\Entity(repositoryClass=ScolariteRepository::class)
 */
class Scolarite
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private ?int $idEtab = null;

    /**
     * @ORM\Column(type="string", length=100)
     * @Assert\NotBlank(message="Le nom ne peut pas être vide")
     * @Assert\Length(max=100, maxMessage="Le nom ne peut pas dépasser {{ limit }} caractères")
     */
    private ?string $nomEtablissement = null;

    /**
     * @ORM\Column(type="string", length=30)
     * @Assert\NotBlank(message="La ville ne peut pas être vide")
     * @Assert\Length(max=30, maxMessage="La ville ne peut pas dépasser {{ limit }} caractères")
     */
    private ?string $ville = null;

    /**
     * @ORM\Column(type="string", length=30)
     * @Assert\NotBlank(message="Le pays ne peut pas être vide")
     * @Assert\Length(max=30, maxMessage="Le pays ne peut pas dépasser {{ limit }} caractères")
     */
    private ?string $pays = null;

    /**
     * @ORM\Column(type="string", length=50)
     * @Assert\NotBlank(message="Le diplôme ne peut pas être vide")
     * @Assert\Length(max=50, maxMessage="Le diplôme ne peut pas dépasser {{ limit }} caractères")
     */
    private ?string $diplome = null;

    /**
     * @ORM\Column(type="date")
     * @Assert\NotBlank(message="La date ne peut pas être vide")
     * @Assert\LessThanOrEqual("today", message="La date ne peut pas être dans le futur")
     */
    private ?\DateTime $dateObtention = null;

      /**
     * @ORM\ManyToOne(targetEntity=User::class)
     */
    private ?User $user = null;

    public function getUser(): ?User
    {
        return $this->user;
    }

    public function setUser(User $user): self
    {
        $this->user = $user;

        return $this;
    }

    public function getIdEtab(): ?int
    {
        return $this->idEtab;
    }

    public function getNomEtablissement(): ?string
    {
        return $this->nomEtablissement;
    }

    public function setNomEtablissement(string $nomEtablissement): self
    {
        $this->nomEtablissement = $nomEtablissement;
        return $this;
    }

    public function getVille(): ?string
    {
        return $this->ville;
    }

    public function setVille(string $ville): self
    {
        $this->ville = $ville;
        return $this;
    }

    public function getPays(): ?string
    {
        return $this->pays;
    }

    public function setPays(string $pays): self
    {
        $this->pays = $pays;
        return $this;
    }

    public function getDiplome(): ?string
    {
        return $this->diplome;
    }

    public function setDiplome(string $diplome): self
    {
        $this->diplome = $diplome;
        return $this;
    }

    public function getDateObtention(): ?\DateTime
    {
        return $this->dateObtention;
    }

    public function setDateObtention(\DateTime $dateObtention): self
    {
        $this->dateObtention = $dateObtention;

        return $this;
    }


}
