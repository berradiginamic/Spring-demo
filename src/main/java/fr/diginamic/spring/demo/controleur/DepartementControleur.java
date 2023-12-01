package fr.diginamic.spring.demo.controleur;

import fr.diginamic.spring.demo.daos.DepartementDao;
import fr.diginamic.spring.demo.services.Departement;
import fr.diginamic.spring.demo.services.Ville;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departements")
public class DepartementControleur {

    private final DepartementDao departementDao;

    public DepartementControleur(DepartementDao departementDao) {
        this.departementDao = departementDao;
    }

    @GetMapping("/tous")
    public List<Departement> extraireTous() {
        return departementDao.extractAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Departement> extractDepartement(@PathVariable Long id) {
        Departement departement = departementDao.extractDepartement(id);
        return departement != null
                ? new ResponseEntity<>(departement, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/departements")
    public ResponseEntity<String> insererDepartement(@RequestBody Departement departement) {
        try {
            departementDao.insert(departement);
            return new ResponseEntity<>("Département inséré avec succès", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Erreur lors de l'insertion du département : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimerDepartement(@PathVariable Long id) {
        Departement departement = departementDao.extractDepartement(id);
        if (departement != null) {
            departementDao.supprimerDepartement(id);
            return new ResponseEntity<>("Département supprimé avec succès", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Aucun département trouvé avec cet ID", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{id}/villes/n-plus-grandes")
    public ResponseEntity<List<Ville>> getNPlusGrandesVilles(@PathVariable Long id, @RequestParam int n) {
        List<Ville> villes = departementDao.getNPlusGrandesVilles(id, n);
        return new ResponseEntity<>(villes, HttpStatus.OK);
    }
}