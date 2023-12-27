package tn.esprit.exam.service;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.exam.entity.Chambre;
import tn.esprit.exam.repository.ChambreRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ChambreServiceImpl implements IChambreService {

    ChambreRepository chambreRepository;

    //@Scheduled(cron = "0/15 * * * * *")
    // @Scheduled(fixedDelay = 10000) // 10000 millisecondes
    public List<Chambre> retrieveAllChambres() {
        List<Chambre> listC = chambreRepository.findAll();
        log.info("nombre total des chambres : " + listC.size());
       for (Chambre c: listC) {
           log.info("chambre : " + c);
       }
        return listC;
    }

    public Chambre retrieveChambre(Long chambreId) {
        Chambre c = chambreRepository.findById(chambreId).get();
        return c;
    }

    public Chambre addChambre(Chambre c) {
        Chambre chambre = chambreRepository.save(c);
        return chambre;
    }

    public Chambre modifyChambre(Chambre chambre) {
        Chambre c = chambreRepository.save(chambre);
        return c;
    }

    public void removeChambre(Long chambreId) {
        chambreRepository.deleteById(chambreId);
    }

}
