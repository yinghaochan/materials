@ ctx.run(query[City].filter(_.id == 3208))
cmd13.sc:1: SELECT x1.id, x1.name, x1.countrycode, x1.district, x1.population
            FROM city x1 WHERE x1.id = 3208
res13: List[City] = List(City(3208, "Singapore", "SGP", "\u0096", 4017733))
