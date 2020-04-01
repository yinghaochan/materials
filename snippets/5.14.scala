@ def splitDate(s: String) = s match{
    case s"$day-$month-$year" =>
      s"day: $day, mon: $month, yr: $year"
    case _ => "not a date"
  }

@ splitDate("9-8-1965")
res174: String = "day: 9, mon: 8, yr: 1965"

@ splitDate("9-8")
res175: String = "not a date"
