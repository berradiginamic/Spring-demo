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
public class DepartementDao {
    @PersistenceContext
    private EntityManager em;

    public List<Departement> extractAll() {
        TypedQuery<Departement> query = em.createQuery("SELECT d FROM Departement d", Departement.class);
        return query.getResultList();
    }

    public Departement extractDepartement(Long id) {
        return em.find(Departement.class, id);
    }

    public List<Ville> getNPlusGrandesVilles(Long departementId, int n) {
        // Logique pour récupérer les N plus grandes villes d'un département
        // Tri des villes par population, puis récupération des N premières
        TypedQuery<Ville> query = em.createQuery(
                "SELECT v FROM Ville v WHERE v.departement.id = :departementId ORDER BY v.nbHabitant DESC",
                Ville.class
        );
        query.setParameter("departementId", departementId);
        query.setMaxResults(n);
        return query.getResultList();
    }

    @Transactional
    public void insert(Departement nouveauDepartement) {
        em.persist(nouveauDepartement);
    }

    @Transactional
    public void supprimerDepartement(Long id) {
        Departement departement = extractDepartement(id);
        if (departement != null) {
            em.remove(departement);
        }
    }

    @Transactional
    public void modifierDepartement(Departement nouveauDepartement) {
        em.merge(nouveauDepartement);
    }
}