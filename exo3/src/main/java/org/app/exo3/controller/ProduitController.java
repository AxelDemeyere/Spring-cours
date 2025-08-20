package org.app.exo3.controller;

import jakarta.servlet.http.HttpSession;
import org.app.exo3.model.dto.ProduitRequestDTO;
import org.app.exo3.model.dto.ProduitResponseDTO;
import org.app.exo3.model.entity.Produit;
import org.app.exo3.service.ProduitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/produits")
public class ProduitController {

    private final ProduitService produitService;

    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @GetMapping
    public ResponseEntity<List<ProduitResponseDTO>> getAllProduits(HttpSession session) {
        Map<Integer, Produit> map = getOrCreateProduitMap(session);
        return ResponseEntity.ok(produitService.getAllProduits(map));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProduitResponseDTO> getProduitById(@PathVariable Long id, HttpSession session) {
        Map<Integer, Produit> map = getOrCreateProduitMap(session);
        return ResponseEntity.ok(produitService.getProduitById(id, map));
    }

    @PostMapping
    public ResponseEntity<ProduitResponseDTO> createProduit(@RequestBody ProduitRequestDTO requestDTO, HttpSession session) {
        Map<Integer, Produit> map = getOrCreateProduitMap(session);
        ProduitResponseDTO result = produitService.createProduit(requestDTO, map);

        session.setAttribute("produitMap", map);

        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProduitResponseDTO> updateProduit(@PathVariable Long id,
                                                            @RequestBody ProduitRequestDTO requestDTO,
                                                            HttpSession session) {
        Map<Integer, Produit> map = getOrCreateProduitMap(session);
        ProduitResponseDTO result = produitService.updateProduit(id, requestDTO, map);
        session.setAttribute("produitMap", map);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable Long id, HttpSession session) {
        Map<Integer, Produit> map = getOrCreateProduitMap(session);
        produitService.deleteProduit(id, map);
        session.setAttribute("produitMap", map);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/panier")
    public ResponseEntity<String> ajouterAuPanier(@PathVariable Long id, HttpSession session) {
        // Vérifier que le produit existe
        Map<Integer, Produit> map = getOrCreateProduitMap(session);
        produitService.getProduitById(id, map); // Lance une exception si le produit n'existe pas

        List<Long> panier = (List<Long>) session.getAttribute("panier");
        if (panier == null) {
            panier = new ArrayList<>();
        }
        panier.add(id);
        session.setAttribute("panier", panier);
        return ResponseEntity.ok("Produit ajouté au panier");
    }

    @GetMapping("/panier")
    public ResponseEntity<List<ProduitResponseDTO>> getPanier(HttpSession session) {
        List<Long> panierIds = (List<Long>) session.getAttribute("panier");
        if (panierIds == null || panierIds.isEmpty()) {
            return ResponseEntity.ok(new ArrayList<>());
        }

        Map<Integer, Produit> map = getOrCreateProduitMap(session);
        List<ProduitResponseDTO> produitsPanier = panierIds.stream()
                .map(id -> produitService.getProduitById(id, map))
                .toList();

        return ResponseEntity.ok(produitsPanier);
    }

    @GetMapping("/panier/total")
    public ResponseEntity<Double> getTotalPanier(HttpSession session) {
        List<Long> panierIds = (List<Long>) session.getAttribute("panier");
        if (panierIds == null || panierIds.isEmpty()) {
            return ResponseEntity.ok(0.0);
        }

        Map<Integer, Produit> map = getOrCreateProduitMap(session);
        double total = panierIds.stream()
                .mapToDouble(id -> produitService.getProduitById(id, map).getPrix())
                .sum();

        return ResponseEntity.ok(total);
    }


    private Map<Integer, Produit> getOrCreateProduitMap(HttpSession session) {
        Map<Integer, Produit> map = (Map<Integer, Produit>) session.getAttribute("produitMap");
        if (map == null) {
            map = new HashMap<>();
            session.setAttribute("produitMap", map);
        }
        return map;
    }
}