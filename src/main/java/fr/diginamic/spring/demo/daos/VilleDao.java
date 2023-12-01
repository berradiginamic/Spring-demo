package fr.diginamic.spring.demo.daos;

import fr.diginamic.spring.demo.services.Departement;
import fr.diginamic.spring.demo.services.Ville;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VilleDao {

    @PersistenceContext
    private EntityManager em;

    public List<Ville> extractAll() {
        TypedQuery<Ville> query = em.createQuery("SELECT v FROM Ville v", Ville.class);
        return query.getResultList();
    }

    public Ville extractVille(Long id) {
        return em.find(Ville.class, id);
    }

    @Transactional
    public void insert(Ville nvVille, Long departementId) {
        Departement departement = em.find(Departement.class, departementId);

        if (departement != null) {
            nvVille.setDepartement(departement);
            em.persist(nvVille);
        } else {
            throw new DepartementNotFoundException("Département non trouvé avec l'ID : " + departementId);
        }
    }
    @Transactional
    public void insert(Ville nvVille) {
        em.persist(nvVille);
    }
}