package com.example.sportsquiz.core.questions

data class Questions(
    val question: List <Pair<String, Answers>>
)

data class Quiz(
    val easy: Questions? = Questions(
        listOf(
            Pair(
                "Which US superstar said ‘I’ve never lost a game I just ran out of time’?",
                Answers(
                    "Michael Jordan", //s
                    "Usain Bolt",
                    "LeBron James",
                    "Cristiano Ronaldo"
                )
            ),
            Pair(
                "What is the first name of Phil and Gary Neville’s father?",
                Answers(
                    "Neville ", //s
                    "John",
                    "Harry",
                    "Michael"
                )
            ),
            Pair(
                "Which country has the most Olympic gold medals in swimming?",
                Answers(
                    "China",
                    "The USA", //s
                    "The UK",
                    "Australia"
                )
            ),
            Pair(
                "When was water polo created?",
                Answers(
                    "20th century",
                    "19th century", //
                    "18th century",
                    "17th century"
                )
            ),
            Pair(
                "How many times did Efren Reyes win the World Pool League championship?",
                Answers(
                    "One",
                    "Two", //
                    "Three",
                    "Four"
                )
            ),
            Pair(
                "What year did boxing become a legal sport? ",
                Answers(
                    "1921",
                    "1901", //
                    "1931",
                    "1911"
                )
            ),
            Pair(
                "Where is the largest bowling centre located?",
                Answers(
                    "US",
                    "Japan", //
                    "Singapore",
                    "Finland"
                )
            )
        )
    ),
    val medium: Questions? = Questions(
        listOf(
            Pair(
                "Of all the fighting sports below, which sport wasn’t practised by Bruce Lee?",
                Answers(
                    "Fencing",
                    "Boxing",
                    "Jeet Kune Do",
                    "Wushu", //
                )
            ),
            Pair(
                "Which basketball player below have their own signature shoes?",
                Answers(
                    "Larry Bird",
                    "Joe Dumars",
                    "Kevin Durant", //
                    "Tiger Woods"
                )
            ),
            Pair(
                "What is the inner edge of an auto-racing track called?",
                Answers(
                    "Airport",
                    "Apron", //
                    "Apex",
                    "Approach"
                )
            ),
            Pair(
                "What country saw the start of the 2010 Tour de France?",
                Answers(
                    "Italy",
                    "Austria",
                    "The Netherlands", //
                    "France"
                )
            ),
            Pair(
                "How long is the route of the Tour de France in kilometers?",
                Answers(
                    "2,400",
                    "1,200",
                    "3,600", //
                    "1,800"
                )
            ),
            Pair(
                "What automobile race is held annually in Indianapolis?",
                Answers(
                    "Le Mans",
                    "The Great Race",
                    "The Grand Prix",
                    "The Indy 500" //
                )
            ),
            Pair(
                "Which of these accompanies a winning racehorse?",
                Answers(
                    "Ribbon pony",
                    "Escort pony",
                    "Winning pony",
                    "Lead pony" //
                )
            ),
        )
    ),
    val hard: Questions? = Questions(
        listOf(
            Pair(
                "Where did the term “billiard” originated from?",
                Answers(
                    "Italy",
                    "France",//
                    "Hungary",
                    "Belgium"
                )
            ),
            Pair(
                "What is the name of a racing event?",
                Answers(
                    "Ultrawalking",
                    "Ultrajogging",
                    "Ultrarunning",//
                    "Ultramarine"
                )
            ),
            Pair(
                "In track, what is the last leg of a relay called?",
                Answers(
                    "Foot race",
                    "Last chart",
                    "Anchor leg",//
                    "Anchorite"
                )
            ),
            Pair(
                "In automobile racing, what color is the flag used to warn drivers to slow down?",
                Answers(
                    "Orange",
                    "Red",
                    "Yellow",//
                    "Blue"
                )
            ),
            Pair(
                "After how many Year’s FIFA World Cup held?",
                Answers(
                    "4 Years",//
                    "2 Years",
                    "3 Years",
                    "Every Year"
                )
            ),
            Pair(
                "Which Sport is Mark Spitz popularly known for?",
                Answers(
                    "Golf",
                    "Snooker",
                    "Swimming", //
                    "Cycling"
                )
            ),
            Pair(
                "Which Sport does Roger Federer play?",
                Answers(
                    "Volleyball",
                    "Tennis",
                    "Table Tennis", //
                    "Badminton"
                )
            ),
        )
    )
)

data class Answers(
    val a: String,
    val b: String,
    val c: String,
    val d: String,
)

data class RightAnswers(
    val easy: List<String> = listOf(
        "Michael Jordan",
        "Neville ",
        "The USA",
        "19th century",
        "Two",
        "1901",
        "Japan",
    ),
    val medium: List<String> = listOf(
        "Wushu",
        "Kevin Durant",
        "Apron",
        "The Netherlands",
        "3,600",
        "The Indy 500",
        "Lead pony",
    ),
    val hard: List<String> = listOf(
        "France",
        "Ultrarunning",
        "Anchor leg",
        "Yellow",
        "4 Years",
        "Swimming",
        "Table Tennis",
    )
)

