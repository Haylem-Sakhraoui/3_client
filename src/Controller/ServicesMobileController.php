<?php

namespace App\Controller;
use App\Entity\Services;
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

#[Route('/servicesMobile')]
class ServicesMobileController extends AbstractController{

    #[Route('/gets', name: 'gets')]
    public function gets(NormalizerInterface $serializer){
        //$marque=$this->getDoctrine()->getManager()->getRepository(Marques::class)->findAll();
        $rep= $this->getDoctrine()->getRepository(Services::class);
        $service=$rep->findAll();
        $formatted=$serializer->normalize($service,'json',['groups'=>'service']);

        return new  Response(json_encode($formatted));
    }

    #[Route('/adds', name: 'adds')]
    public function addc(Request $request, SerializerInterface  $serializer , EntityManagerInterface $em)
    {
        $service = new Services();

         $nom= $request->query->get("nom");   
         $nb=   $request->query->get("nb");

        $em = $this->getDoctrine()->getManager();

        $service->setNomService($nom);
        $service->setNbTotFreelance($nb);

        $em->persist($service);
        $em->flush();

        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($service);
        return new JsonResponse($formatted);
    }

    #[Route('/updates', name: 'updates')]
    public function updatec(Request $request,NormalizerInterface $serializer,EntityManagerInterface $em)
    {
        $nom= $request->query->get("nom");   
        $nb=   $request->query->get("nb");

        $em=$this->getDoctrine()->getManager();
        $m=$this->getDoctrine()->getManager()->getRepository(Services::class)->find($request->get("id"));

        $m->setNomService($nom);
        $m->setNbTotFreelance($nb);

        $em->persist($m);
        $em->flush();
        //$serializer=new Serializer([new ObjectNormalizer()]);
        $formatted=$serializer->normalize($m,'json',['groups'=>'categorie']);
        return new JsonResponse('Categorie updated');
    }

    #[Route('/deletes', name: 'deletes')]
    public function deletec(Request $request, SerializerInterface  $serializer , EntityManagerInterface $em)
    {
        $id = $request->get("id");
        $em = $this->getDoctrine()->getManager();
        $service  = $em->getRepository(Services::class)->find($id);
        if($service != null)
        {
            $em->remove($service);
            $em->flush();
            $serializer = new Serializer([new ObjectNormalizer()]);
            $formatted = $serializer->normalize("categorie Deleted ");
            return new JsonResponse($formatted);
        }
        return new JsonResponse("rip");
    }

    #[Route('/searchservice/{searchString}', name: 'searchservice')]
    public function searchservice($searchString,NormalizerInterface $serializerInterface ,Request $request,EntityManagerInterface $entityManager)
    {
        //$serializer=new Serializer([new ObjectNormalizer()]);
        // $serializer = new Serializer([new ObjectNormalizer()]);
        
        // $students = $repository->findByid($searchString);
        //$students = $repository->findBy(array('id' => '%'.'2'));
        $queryBuilder = $entityManager->createQueryBuilder();
        $queryBuilder->select('s')
            ->from('App\Entity\Services', 's')
            ->where('s.nomService LIKE :nomservice')
            ->setParameter('nomservice', '%' . $searchString . '%');

        $services = $queryBuilder->getQuery()->getResult();

        $servicesArray = [];
        foreach ($services as $service) {
            $servicesArray[] = [
                'id' => $service->getIdservice(),
                'nomService' => $service->getNomService(),
                'nbTotFreelance' => $service->getNbTotFreelance()
            ];
        }

        $data = $serializerInterface->normalize($services,'json',['groups'=>'service']);
        // $data=$serializer->normalize("");

        return new JsonResponse($data);
    }

    


}