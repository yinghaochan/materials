@ ctx.run(query[City].filter(_.name == "Singapore"))
cmd6.sc:1: SELECT x1.id, x1.name, x1.countrycode, x1.district, x1.population
           FROM city x1 WHERE x1.name = 'Singapore'
res6: List[City] = List(City(3208, "Singapore", "SGP", "\u0096", 4017733))
