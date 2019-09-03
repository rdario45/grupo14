package infraestructure.services;

import com.fasterxml.jackson.databind.JsonNode;
import controllers.dto.CreateAccountDTO;
import domain.Design;
import domain.Company;
import infraestructure.acl.account.AccountBuilder;
import infraestructure.acl.project.ProjectMapper;
import infraestructure.repository.design.DesignRepository;
import io.vavr.Tuple2;
import io.vavr.concurrent.Future;
import io.vavr.control.Either;
import play.Logger;
import play.mvc.Result;
import play.mvc.Results;

import javax.inject.Inject;
import java.util.List;

import static play.mvc.Http.Context.Implicit.request;

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


}
