
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import static java.lang.reflect.Modifier.TRANSIENT;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import susiburimuapp.Renginys;
import susiburimuapp.Sistema;
import susiburimuapp.Vartotojas;

@Controller
public class Kontroleris {

    @RequestMapping(value = "vartotojas={vid}")
    @ResponseBody
    public String vartotojas(@PathVariable int vid) {
        Sistema s = new Sistema();
        Vartotojas vartotojas = s.gautiVartotoja(vid);
        final Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .excludeFieldsWithModifiers(TRANSIENT) // STATIC|TRANSIENT in the default configuration
                .create();
        String json = gson.toJson(vartotojas);
        System.out.println(json);
        return json;
    }
 @RequestMapping(value = "vartotojai")
    @ResponseBody
    public String vartotojai() {
        Sistema s = new Sistema();
        List<Vartotojas> vartotojas = s.getVartotojai();
        final Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .excludeFieldsWithModifiers(TRANSIENT) // STATIC|TRANSIENT in the default configuration
                .create();
        String json = gson.toJson(vartotojas);
        System.out.println(json);
        return json;
    }
    
    @RequestMapping(value = "renginiai")
    @ResponseBody
    public String renginiai() {
        Sistema s = new Sistema();
        List<Renginys> renginiai = s.getRenginiai();
        final Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .excludeFieldsWithModifiers(TRANSIENT) // STATIC|TRANSIENT in the default configuration
                .create();
        String json = gson.toJson(renginiai);
        System.out.println(json);
        return json;
    }
    
     @RequestMapping(value = "renginys={id}")
    @ResponseBody
    public String renginys(@PathVariable int id) {
        Sistema s = new Sistema();
        Renginys renginys = s.gautiRengini(id);
        final Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .excludeFieldsWithModifiers(TRANSIENT) // STATIC|TRANSIENT in the default configuration
                .create();
        String json = gson.toJson(renginys);
        System.out.println(json);
        return json;
    }
    
     @RequestMapping(value = "priskirti={id}&renginys={rid}")
    @ResponseBody
    public String priskirimas(@PathVariable("id") int id,@PathVariable("rid") int rid) {
        Sistema s = new Sistema();
        Renginys renginys = s.gautiRengini(rid);
        Vartotojas vartotojas = s.gautiVartotoja(id);
        s.priskirtiVartotojaPrieRenginio(vartotojas, renginys);
        return "Aciu uz dalyvavima";
    }
}
