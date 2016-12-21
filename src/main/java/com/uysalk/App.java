package com.uysalk;

import org.jooby.Jooby;
import org.jooby.json.Jackson;

/**
 * Created by uysal.kara on 21.12.2016.
 */
public class App extends Jooby{


    {

        use(new Jackson());
        assets("/", "index.html");
        assets("/**");

        get("/hw", req -> {
            return "Hello world";
            }
        );


    }

    public static void main(final String[] args) throws Throwable {
        run(App::new, args);
    }
}
