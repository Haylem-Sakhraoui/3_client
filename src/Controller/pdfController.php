<?php

namespace App\Controller;

use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\ResponseHeaderBag;
use Dompdf\Dompdf;
use Dompdf\Options;

class pdfController extends AbstractController
{
#[Route('/pdf', name: 'pdf')]
    public function generatePdf(): Response
    {
       
       
        
        $html = '
            <html>
            <head>
                <style>
                    .blue-title {
                        color: blue;
                        font-size: 24px;
                    }
                    .red-title {
                        color: red;
                        font-size: 36px;
                    }
                    .subtitle {
                        font-size: 18px;
                    }
                </style>
            </head>
            <body>
            <div class="row">
</div>

    <div class="row">
        <div class="col-md-4">
            <div class="card">
                <div class="card-body">
                <div class="card-body">
                <h2>Liste des compétences </h2>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Nom</th>
                                
                            </tr>
                        </thead>
                        <tbody>
                                                            <tr>
                                    <td>SGBD</td>
                                
                                </tr>
                                                            <tr>
                                    <td>symfony</td>
   
                                </tr>
                                                            <tr>
                                    <td>react</td>
                                </tr>
                                                            <tr>
                                    <td>html</td>
                                </tr>
                                                            <tr>
                                    <td>html</td>
                                </tr>
                                                    </tbody>
                    </table>

                    <div class="card-body">
                    <h2>Liste des expériences professionnelles</h2>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Nom de entreprise</th>
                                <th>Poste</th>
                                <th>Date de début</th>
                                <th>Date de fin</th>
                               
                            </tr>
                        </thead>
                        <tbody>
                                                            <tr>
                                    <td>meta</td>
                                    <td>fullstack</td>
                                    <td>2023-03-09</td>
                                    <td>2023-03-16</td>
                                  
                                </tr>
                                                            <tr>
                                    <td>google</td>
                                    <td>cloud</td>
                                    <td>2023-03-03</td>
                                    <td>2024-03-14</td>
                                    
                                </tr>
                                                            <tr>
                                    <td>test</td>
                                    <td>test</td>
                                    <td>2018-01-01</td>
                                    <td>2018-01-01</td>
                                    
                                </tr>
                                                    </tbody>
                    </table>
                    </div> 

                </div>

            </body>
            </html>';
    
        
        $dompdf = new Dompdf();
        $dompdf->loadHtml($html);
        $dompdf->setPaper('A4', 'portrait');
        $dompdf->render();
    
      
        $pdfContent = $dompdf->output();
        $response = new Response();
        $response->setContent($pdfContent);
        $response->headers->set('Content-Type', 'application/pdf');
    
       
        $disposition = $response->headers->makeDisposition(
            ResponseHeaderBag::DISPOSITION_ATTACHMENT,
            'portfolio1.pdf'
        );
        $response->headers->set('Content-Disposition', $disposition);
    
        return $response;
    }
}