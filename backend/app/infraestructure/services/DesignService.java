package infraestructure.services;

import domain.Design;
import infraestructure.repository.design.DesignRepository;
import io.vavr.concurrent.Future;

import javax.inject.Inject;
import java.util.List;

public class DesignService {

    private DesignRepository designRepository;

    @Inject
    public DesignService(DesignRepository designRepository) {
        this.designRepository = designRepository;
    }

    public List<Design> getPendingDesigns(){
        return designRepository.findPendingAll().toJavaList();
    }

    public void  updateDesign(Design design) {
        designRepository.update(design);
    }

    public Future<Design> createDesign(Design design){
        return designRepository.create(design);
    }

}
