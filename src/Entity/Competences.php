<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use App\Repository\CompetenceRepository;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Serializer\Annotation\Groups;

/**
 * @ORM\Entity(repositoryClass=CompetenceRepository::class)
 */
class Competences
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    #[Groups(['competence'])]
    private ?int $idComp = null;

    /**
     * @ORM\Column(type="string", length=100)
     * @Assert\NotBlank(message="Le nom ne peut pas être vide")
     * @Assert\Length(max=100, maxMessage="Le nom ne peut pas dépasser {{ limit }} caractères")
     */
    #[Groups(['competence'])]
    private ?string $nom;

     /**
     * @ORM\ManyToOne(targetEntity=User::class)
     */
    #[Groups(['competence'])]
    public ?User $user = null;

    
    public function getIdComp(): ?int
    {
        return $this->idComp;
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): self
    {
        $this->nom = $nom;

        return $this;
    }
    public function getUser(): ?User
    {
        return $this->user;
    }

    public function setUser(User $user): self
    {
        $this->user = $user;

        return $this;
    }

    public function __toString()
    {
        return $this->getNom();
    }
}
