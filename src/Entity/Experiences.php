<?php

namespace App\Entity;

use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;
use App\Repository\ExperienceRepository;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * @ORM\Entity(repositoryClass=ExperienceRepository::class)
 */
class Experiences
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private ?int $idExp = null;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Le nom de l'entreprise ne peut pas être vide.")
     * @Assert\Length(max=100, maxMessage="Le nom de l'entreprise ne peut pas dépasser {{ limit }} caractères.")
     * @Assert\Regex(pattern="/^[a-zA-Z0-9\s\-]*$/", message="Le nom de l'entreprise n'est pas valide.")
     */
    private ?string $nomEntreprise;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Le poste ne peut pas être vide.")
     * @Assert\Length(max=100, maxMessage="Le poste ne peut pas dépasser {{ limit }} caractères.")
     * @Assert\Regex(pattern="/^[a-zA-Z0-9\s\-]*$/", message="Le nom du poste n'est pas valide.")
     */
    private ?string $poste;

    /**
     * @ORM\Column(type="date")
     * @Assert\LessThanOrEqual("today", message="La date de début doit être antérieure ou égale à aujourd'hui.")
     * @Assert\NotNull(message="La date de début ne peut pas être vide.")
     */
    private ?\DateTime $dateDebut;

    /**
     * @ORM\Column(type="date")
     * @Assert\NotNull(message="La date de fin ne peut pas être vide.")
     * @Assert\Expression(
     *     "value >= this.getDateDebut()",
     *     message="La date de fin doit être postérieure ou égale à la date de début."
     * )
     */
    private ?\DateTime $dateFin;

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
    public function getIdExp(): ?int
    {
        return $this->idExp;
    }

    public function getNomEntreprise(): ?string
    {
        return $this->nomEntreprise;
    }

    public function setNomEntreprise(string $nomEntreprise): self
    {
        $this->nomEntreprise = $nomEntreprise;

        return $this;
    }

    public function getPoste(): ?string
    {
        return $this->poste;
    }

    public function setPoste(string $poste): self
    {
        $this->poste = $poste;

        return $this;
    }

    public function getDateDebut(): ?\DateTime
    {
        return $this->dateDebut;
    }

    public function setDateDebut(\DateTime $dateDebut): self
    {
        $this->dateDebut = $dateDebut;

        return $this;
    }

    public function getDateFin(): ?\DateTime
    {
        return $this->dateFin;
    }

    public function setDateFin(\DateTime $dateFin): self
    {
        $this->dateFin = $dateFin;

        return $this;
    }
}
