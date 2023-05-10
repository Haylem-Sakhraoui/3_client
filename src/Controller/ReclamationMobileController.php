<?php

namespace App\Controller;

use App\Entity\Reclamation;
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


#[Route('/reclamationMobile')]
class ReclamationMobileController extends AbstractController{

    #[Route('/getr', name: 'getr')]
    public function getr(NormalizerInterface $serializer){
        //$marque=$this->getDoctrine()->getManager()->getRepository(Marques::class)->findAll();
        $rep= $this->getDoctrine()->getRepository(Reclamation::class);
        $reclamation=$rep->findAll();
        $formatted=$serializer->normalize($reclamation,'json',['groups'=>'reclamation']);

        return new  Response(json_encode($formatted));
    }

    #[Route('/addr', name: 'addr')]
    public function addr(Request $request, SerializerInterface  $serializer , EntityManagerInterface $em)
    {
         $reclamation = new Reclamation();

         $description= $request->query->get("description");   
         $d = new DateTime();
     
         $em = $this->getDoctrine()->getManager();

        $reclamation->setDate($d);
        $reclamation->setDescription($description);
    

        $em->persist($reclamation);
        $em->flush();

        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($reclamation);
        return new JsonResponse($formatted);
    }

    #[Route('/updater', name: 'updater')]
    public function updater(Request $request, SerializerInterface  $serializer , EntityManagerInterface $em)
    {
         

         $description= $request->query->get("description");   
         $d = new DateTime();
     
         $em = $this->getDoctrine()->getManager();


         $em=$this->getDoctrine()->getManager();
         $reclamation=$this->getDoctrine()->getManager()->getRepository(Reclamation::class)->find($request->get("id"));

       
       
         $reclamation->setDate($d);
        $reclamation->setDescription($description);

        $em->persist($reclamation);
        $em->flush();

        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted=$serializer->normalize($reclamation,'json',['groups'=>'reclamation']);
        return new JsonResponse('Reclamation updated');
    }




    #[Route('/deleter', name: 'deleter')]
    public function deleter(Request $request, SerializerInterface  $serializer , EntityManagerInterface $em)
    {
        $id = $request->get("id");
        $em = $this->getDoctrine()->getManager();
        $demande  = $em->getRepository(Reclamation::class)->find($id);
        if($demande != null)
        {
            $em->remove($demande);
            $em->flush();
            $serializer = new Serializer([new ObjectNormalizer()]);
            $formatted = $serializer->normalize("Reclamation Deleted ");
            return new JsonResponse($formatted);
        }
        return new JsonResponse("rip");
    }

    #[Route('/sortReclamation/{search}', name: 'sortReclamatio')]
    public function sortReclamatio($search,NormalizerInterface $serializerInterface ,Request $request,EntityManagerInterface $entityManager)
    {
        //$serializer=new Serializer([new ObjectNormalizer()]);
        // $serializer = new Serializer([new ObjectNormalizer()]);
        
        // $students = $repository->findByid($searchString);
        //$students = $repository->findBy(array('id' => '%'.'2'));
        $queryBuilder = $entityManager->createQueryBuilder();
        
        if($search==1){
            $queryBuilder->select('r')
                ->from('App\Entity\Reclamation', 'r')
                ->orderBy('r.date','ASC');
           }

        if($search==2){
        $queryBuilder->select('r')
            ->from('App\Entity\Reclamation', 'r')
            ->orderBy('r.date', 'DESC');
       }

        $reclamation = $queryBuilder->getQuery()->getResult();

        $data = $serializerInterface->normalize($reclamation,'json',['groups'=>'reclamation']);
        // $data=$serializer->normalize("");

        return new JsonResponse($data);
    }

}