package servlets;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SkiersServlet", value = "/skiers")
public class SkiersServlet extends HttpServlet {

  private final Gson gson = new Gson();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res)
      throws IOException {
    res.setContentType("application/json");
    String urlPath = req.getPathInfo();
    if (urlPath == null || urlPath.isEmpty()) {
      res.setStatus(HttpServletResponse.SC_NOT_FOUND);
      res.getWriter().write(gson.toJson(new Message("Missing Parameters!")));
      return;
    }
    String[] urlSplit = urlPath.split("/");
    if (isUrlNotValid(urlSplit)) {
      res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      res.getWriter().write(gson.toJson(new Message("Invalid Inputs! Or Invalid Path! ")));
    } else {
      res.setStatus(HttpServletResponse.SC_OK);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res)
      throws IOException, NumberFormatException {
    res.setContentType("application/json");
    String urlPath = req.getPathInfo();
    if (urlPath == null || urlPath.isEmpty()) {
      res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      res.getWriter().write(gson.toJson(new Message("Missing Parameters!")));
      return;
    }
    String[] urlPaths = urlPath.split("/");
    JsonObject body = gson.fromJson(req.getReader(), JsonObject.class);
//    System.out.println(body.toString() + Arrays.toString(urlPaths));
    if (isUrlNotValid(urlPaths) || isBodyNotValid(body)) {
      res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      res.getWriter().write(gson.toJson(new Message("Invalid Inputs! Or Invalid Path!")));
    } else {
      res.setStatus(HttpServletResponse.SC_CREATED);
      res.getWriter().write(gson.toJson(new Message("Record is created in the database.")));
    }
  }

  private boolean isBodyNotValid(JsonObject body) {
    JsonElement time = body.get("time");
    JsonElement liftID = body.get("liftID");

    return time == null || liftID == null
        || time.getAsInt() < 1 || time.getAsInt() > 360
        || liftID.getAsInt() < 1 || liftID.getAsInt() > 40;
  }

  private boolean isUrlNotValid(String[] urlPath) {
    int resortId = Integer.parseInt(urlPath[1]);
    int seasonId = Integer.parseInt(urlPath[3]);
    int dayId = Integer.parseInt(urlPath[5]);
    int skierId = Integer.parseInt(urlPath[7]);
    return urlPath.length != 8
        || resortId < 1 || resortId > 10
        || !urlPath[2].equals("seasons")
        || seasonId != 2022
        || !urlPath[4].equals("days")
        || dayId != 1
        || !urlPath[6].equals("skiers")
        || skierId < 1 || skierId > 100000;
  }
}
