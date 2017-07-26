package controller;

import javassist.NotFoundException;
import model.Faction;
import org.omg.CORBA.SystemException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author David
 */
@org.springframework.stereotype.Controller
public class Controller {

    @RequestMapping(value = "/units", method = GET)
    public List<String> getUnits(@RequestParam(value = "faction", required = true) String factionName, Model model) {

        List<String> availableUnits = new ArrayList<>();

        //TODO: move this to a repo
        if (factionName.equals("deathGuard")) {
            availableUnits.add("Poxwalkers");
            availableUnits.add("Plague Marines");
        } else if (factionName.equals("spaceMarines")) {
            availableUnits.add("Space Marines");
            availableUnits.add("Scouts");
        }

        model.addAttribute("availableUnits", availableUnits);

        return availableUnits;
    }

    @RequestMapping(value = "/factions", method = GET)
    public HttpEntity<List<Faction>> getFactions() {
        List<Faction> availableFactions = new ArrayList<>();

        //TODO: move this to a repo
        Faction deathGuard = new Faction("deathGuard", "Death Guard");
        Faction spaceMarines = new Faction("spaceMarines", "Space Marines");

        availableFactions.add(deathGuard);
        availableFactions.add(spaceMarines);

        for (Faction faction : availableFactions) {
            faction.add(linkTo(methodOn(Controller.class).getFaction(faction.getFactionId())).withSelfRel());
        }

        return new ResponseEntity<List<Faction>>(availableFactions, HttpStatus.OK);
    }

    @RequestMapping(value = "/faction", method = GET)
    public HttpEntity<Faction> getFaction(@RequestParam(value = "factionId", required = true) String factionId) {
        Faction requestedFaction = null;

        if (factionId.equals("deathGuard")) {
            requestedFaction = new Faction("deathGuard", "Death Guard");
        } else if (factionId.equals("spaceMarines")) {
            requestedFaction = new Faction("spaceMarines", "Space Marines");
        }

        return new ResponseEntity<Faction>(requestedFaction, HttpStatus.OK);
    }
}
