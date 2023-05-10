<?php

namespace App\Controller;

use App\Entity\Demande;
use App\Entity\User;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Validator\Constraints\Json;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\SerializerInterface;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use DateTime;


#[Route('/demandeMobile')]
class DemandeMobilleController extends AbstractController{

    #[Route('/getd', name: 'getd')]
    public function getd(NormalizerInterface $serializer){
        //$marque=$this->getDoctrine()->getManager()->getRepository(Marques::class)->findAll();
        $rep= $this->getDoctrine()->getRepository(Demande::class);
        $service=$rep->findAll();
        $formatted=$serializer->normalize($service,'json',['groups'=>'demande']);

        return new  Response(json_encode($formatted));
    }


    #[Route('/addD', name: 'addD')]
    public function addc(Request $request, SerializerInterface  $serializer , EntityManagerInterface $em)
    {
         $demande = new Demande();

         $nomRecruteur= $request->query->get("nomRecruteur");   
         $description=   $request->query->get("description");
         $experience=   $request->query->get("experience");
         $remuneration=   $request->query->get("remuneration");
         $telephone=   $request->query->get("telephone");
         $expiration=   new DateTime($request->query->get("expiration"));
         $idRecruteur=   $request->query->get("idRecruteur");
        
         $rep= $this->getDoctrine()->getRepository(User::class);
         $user = $rep->find($idRecruteur);
         $em = $this->getDoctrine()->getManager();

      $demande->setNomRecruteur($nomRecruteur);
      $demande->setDescription($description);
      $demande->setExperience($experience);
      $demande->setRemuneration($remuneration);
      $demande->setTelephone($telephone);
      $demande->setExpiration($expiration);
      $demande->setIdRecruteur($user);

        $em->persist($demande);
        $em->flush();

        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($demande);
        return new JsonResponse($formatted);
    }

    #[Route('/updated', name: 'updated')]
    public function updatec(Request $request,NormalizerInterface $serializer,EntityManagerInterface $em)
    {
    

     
        $nomRecruteur= $request->query->get("nomRecruteur");   
        $description=   $request->query->get("description");
        $experience=   $request->query->get("experience");
        $remuneration=   $request->query->get("remuneration");
        $telephone=   $request->query->get("telephone");
        $expiration=   new DateTime($request->query->get("expiration"));
        $idRecruteur=   $request->query->get("idRecruteur");
       
        $rep= $this->getDoctrine()->getRepository(User::class);
        $user = $rep->find($idRecruteur);
     

  


        $em=$this->getDoctrine()->getManager();
        $demande=$this->getDoctrine()->getManager()->getRepository(Demande::class)->find($request->get("id"));

        $demande->setNomRecruteur($nomRecruteur);
        $demande->setDescription($description);
        $demande->setExperience($experience);
        $demande->setRemuneration($remuneration);
        $demande->setTelephone($telephone);
        $demande->setExpiration($expiration);
        $demande->setIdRecruteur($user);

        $em->persist($demande);
        $em->flush();
        //$serializer=new Serializer([new ObjectNormalizer()]);
        $formatted=$serializer->normalize($demande,'json',['groups'=>'demande']);
        return new JsonResponse('Demande updated');
    }

    #[Route('/deleted', name: 'deleted')]
    public function deleted(Request $request, SerializerInterface  $serializer , EntityManagerInterface $em)
    {
        $id = $request->get("id");
        $em = $this->getDoctrine()->getManager();
        $demande  = $em->getRepository(Demande::class)->find($id);
        if($demande != null)
        {
            $em->remove($demande);
            $em->flush();
            $serializer = new Serializer([new ObjectNormalizer()]);
            $formatted = $serializer->normalize("Demande Deleted ");
            return new JsonResponse($formatted);
        }
        return new JsonResponse("rip");
    }


    #[Route('/searchdemande/{searchString}', name: 'searchdemande')]
    public function searchdemande($searchString,NormalizerInterface $serializerInterface ,Request $request,EntityManagerInterface $entityManager)
    {
        //$serializer=new Serializer([new ObjectNormalizer()]);
        // $serializer = new Serializer([new ObjectNormalizer()]);
        
        // $students = $repository->findByid($searchString);
        //$students = $repository->findBy(array('id' => '%'.'2'));
        $queryBuilder = $entityManager->createQueryBuilder();
        $queryBuilder->select('d')
            ->from('App\Entity\Demande', 'd')
            ->where('d.nomRecruteur LIKE :nomrecruteur')
            ->setParameter('nomrecruteur', '%' . $searchString . '%');

        $demande = $queryBuilder->getQuery()->getResult();

        $data = $serializerInterface->normalize($demande,'json',['groups'=>'demande']);
        // $data=$serializer->normalize("");

        return new JsonResponse($data);
    }




}



