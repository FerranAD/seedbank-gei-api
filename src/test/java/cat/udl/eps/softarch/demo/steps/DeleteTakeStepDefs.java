package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Take;
import cat.udl.eps.softarch.demo.repository.TakeRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class DeleteTakeStepDefs {
    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private TakeRepository takeRepository;
    private void createNewTake() throws Throwable {
        Take take = new Take();
        take.setTakeDate(ZonedDateTime.now());
        take.setId(1);
        take.setAmount(5);
        take.setWeight(new BigDecimal("5"));
        take.setLocation("Lleida");

        stepDefs.result = stepDefs.mockMvc.perform(
                post("/takes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(stepDefs.mapper.writeValueAsString(take))
        );
    }

    @Given("^There is Take available with id (\\d+)$")
    public void thereIsTakeAvailableWithId(Long id) throws Throwable {
        createNewTake();
        Assert.assertEquals("Take with this id", this.takeRepository.findById(id), Optional.empty());
    }

    @When("^I delete Take with id (\\d+)$")
    public void iDeleteATake(Long id) throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(
                post("/takes/{id}", id)
                        .accept(MediaType.APPLICATION_JSON)
        );
    }
}
