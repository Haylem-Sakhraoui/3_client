<?php

namespace App\Controller;
use App\Entity\Services;
use App\Entity\Competences;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
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

#[Route('/competenceMobile')]
class CompetenceMobileController extends AbstractController{

    #[Route('/getcomp', name: 'getscomp')]
    public function gets(NormalizerInterface $serializer){
        //$marque=$this->getDoctrine()->getManager()->getRepository(Marques::class)->findAll();
        $rep= $this->getDoctrine()->getRepository(Competences::class);
        $competence=$rep->findAll();
        $formatted=$serializer->normalize($competence,'json',['groups'=>'competence']);

        return new  Response(json_encode($formatted));
    }

    #[Route('/adds', name: 'adds')]
    public function addc(Request $request, SerializerInterface  $serializer , EntityManagerInterface $em)
    {
        $competence = new Competences();

         $nom= $request->query->get("nom");

        $em = $this->getDoctrine()->getManager();

        $competence->setNom($nom);

        $em->persist($competence);
        $em->flush();

        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($competence);
        return new JsonResponse($formatted);
    }

    #[Route('/updates', name: 'updates')]
    public function updatec(Request $request,NormalizerInterface $serializer,EntityManagerInterface $em)
    {
        $nom= $request->query->get("nom");   
        

        $em=$this->getDoctrine()->getManager();
        $m=$this->getDoctrine()->getManager()->getRepository(Competences::class)->find($request->get("idComp"));

        $m->setNom($nom);
        
        $em->persist($m);
        $em->flush();
        //$serializer=new Serializer([new ObjectNormalizer()]);
        $formatted=$serializer->normalize($m,'json',['groups'=>'competence']);
        return new JsonResponse('Competence updated');
    }

    #[Route('/deletes', name: 'deletes')]
    public function deletec(Request $request, SerializerInterface  $serializer , EntityManagerInterface $em)
    {
        $id = $request->get("idComp");
        $em = $this->getDoctrine()->getManager();
        $competence  = $em->getRepository(Competences::class)->find($id);
        if($competence != null)
        {
            $em->remove($competence);
            $em->flush();
            $serializer = new Serializer([new ObjectNormalizer()]);
            $formatted = $serializer->normalize("competence Deleted ");
            return new JsonResponse($formatted);
        }
        return new JsonResponse("rip");
    }

    // #[Route('/searchservice/{searchString}', name: 'searchservice')]
    // public function searchservice($searchString,NormalizerInterface $serializerInterface ,Request $request,EntityManagerInterface $entityManager)
    // {
    //     //$serializer=new Serializer([new ObjectNormalizer()]);
    //     // $serializer = new Serializer([new ObjectNormalizer()]);
        
    //     // $students = $repository->findByid($searchString);
    //     //$students = $repository->findBy(array('id' => '%'.'2'));
    //     $queryBuilder = $entityManager->createQueryBuilder();
    //     $queryBuilder->select('s')
    //         ->from('App\Entity\Services', 's')
    //         ->where('s.nomService LIKE :nomservice')
    //         ->setParameter('nomservice', '%' . $searchString . '%');

    //     $services = $queryBuilder->getQuery()->getResult();

    //     $servicesArray = [];
    //     foreach ($services as $service) {
    //         $servicesArray[] = [
    //             'id' => $service->getIdservice(),
    //             'nomService' => $service->getNomService(),
    //             'nbTotFreelance' => $service->getNbTotFreelance()
    //         ];
    //     }

    //     $data = $serializerInterface->normalize($services,'json',['groups'=>'service']);
    //     // $data=$serializer->normalize("");

    //     return new JsonResponse($data);
    // }

    


}
