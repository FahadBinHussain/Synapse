package com.knilaruen.synapse.data

object TriviaRepository {
    
    private val triviaFacts = listOf(
        TriviaFact(1, "A single bolt of lightning contains enough energy to toast 100,000 slices of bread.", Category.SCIENCE),
        TriviaFact(2, "Honey never spoils. Archaeologists found 3,000-year-old honey in Egyptian tombs that was still perfectly edible.", Category.NATURE),
        TriviaFact(3, "Bananas are berries, but strawberries aren't.", Category.NATURE),
        TriviaFact(4, "Your brain uses about 20% of your body's oxygen and calories, despite being only 2% of your body weight.", Category.HUMAN_BODY),
        TriviaFact(5, "A day on Venus is longer than a year on Venus.", Category.SPACE),
        TriviaFact(6, "Octopuses have three hearts and blue blood.", Category.ANIMALS),
        TriviaFact(7, "The Eiffel Tower can be 15 cm taller during summer due to thermal expansion.", Category.SCIENCE),
        TriviaFact(8, "Cleopatra lived closer to the Moon landing than to the construction of the Great Pyramid.", Category.HISTORY),
        TriviaFact(9, "A cloud can weigh more than a million pounds.", Category.NATURE),
        TriviaFact(10, "There are more possible iterations of a game of chess than there are atoms in the known universe.", Category.QUIRKY),
        TriviaFact(11, "Sharks existed before trees. Sharks have been around for 400 million years, while trees evolved 350 million years ago.", Category.HISTORY),
        TriviaFact(12, "The human eye can distinguish about 10 million different colors.", Category.HUMAN_BODY),
        TriviaFact(13, "A teaspoon of neutron star material would weigh about 6 billion tons.", Category.SPACE),
        TriviaFact(14, "Butterflies can taste with their feet.", Category.ANIMALS),
        TriviaFact(15, "The world's oldest known recipe is for beer, dating back to 5,000 BC in ancient Mesopotamia.", Category.HISTORY),
        TriviaFact(16, "Glass is technically a liquid, just an extremely slow-moving one.", Category.SCIENCE),
        TriviaFact(17, "A single strand of spaghetti is called a 'spaghetto'.", Category.LANGUAGE),
        TriviaFact(18, "Your nose can remember 50,000 different scents.", Category.HUMAN_BODY),
        TriviaFact(19, "Polar bears have black skin under their white fur.", Category.ANIMALS),
        TriviaFact(20, "The shortest war in history lasted 38 to 45 minutes between Britain and Zanzibar in 1896.", Category.HISTORY),
        TriviaFact(21, "There's enough DNA in your body to stretch from Earth to the Sun and back more than 300 times.", Category.SCIENCE),
        TriviaFact(22, "Sea otters hold hands while sleeping so they don't drift apart.", Category.ANIMALS),
        TriviaFact(23, "The Amazon rainforest produces 20% of the world's oxygen.", Category.NATURE),
        TriviaFact(24, "A bolt of lightning is five times hotter than the surface of the sun.", Category.SCIENCE),
        TriviaFact(25, "The heart of a blue whale is so large that a human could swim through its arteries.", Category.ANIMALS),
        TriviaFact(26, "There are more stars in the universe than grains of sand on all of Earth's beaches.", Category.SPACE),
        TriviaFact(27, "Water can boil and freeze at the same time in a phenomenon called the 'triple point'.", Category.SCIENCE),
        TriviaFact(28, "A group of flamingos is called a 'flamboyance'.", Category.ANIMALS),
        TriviaFact(29, "The longest word you can type using only the top row of a keyboard is 'typewriter'.", Category.QUIRKY),
        TriviaFact(30, "Your stomach acid is strong enough to dissolve razor blades.", Category.HUMAN_BODY),
        TriviaFact(31, "The moon has moonquakes just like Earth has earthquakes.", Category.SPACE),
        TriviaFact(32, "Dolphins have names for each other and call out to one another specifically.", Category.ANIMALS),
        TriviaFact(33, "A single cloud can weigh as much as 500,000 kg - the weight of 100 elephants.", Category.NATURE),
        TriviaFact(34, "The fingerprints of koala bears are virtually indistinguishable from human fingerprints.", Category.ANIMALS),
        TriviaFact(35, "If you could fold a piece of paper 42 times, it would reach the moon.", Category.QUIRKY),
        TriviaFact(36, "A day on Mercury lasts 176 Earth days, but a year on Mercury is just 88 Earth days.", Category.SPACE),
        TriviaFact(37, "The human brain is 73% water. It takes only 2% dehydration to affect attention, memory and cognitive skills.", Category.HUMAN_BODY),
        TriviaFact(38, "Antarctica is the largest desert in the world.", Category.GEOGRAPHY),
        TriviaFact(39, "A shrimp's heart is located in its head.", Category.ANIMALS),
        TriviaFact(40, "The first computer bug was an actual bug - a moth trapped in a computer in 1947.", Category.TECHNOLOGY),
        TriviaFact(41, "Trees can communicate with each other through an underground network of fungi.", Category.NATURE),
        TriviaFact(42, "The speed of light is so fast that it could circle the Earth 7.5 times in one second.", Category.SCIENCE),
        TriviaFact(43, "A group of crows is called a 'murder'.", Category.ANIMALS),
        TriviaFact(44, "The Great Wall of China is not visible from space with the naked eye.", Category.HISTORY),
        TriviaFact(45, "Your body has enough iron in it to make a 3-inch nail.", Category.HUMAN_BODY),
        TriviaFact(46, "Pineapples take about two years to grow.", Category.NATURE),
        TriviaFact(47, "The coldest temperature ever recorded on Earth was -128.6°F in Antarctica.", Category.GEOGRAPHY),
        TriviaFact(48, "A single Google search uses more computing power than was used to send Apollo 11 to the moon.", Category.TECHNOLOGY),
        TriviaFact(49, "Sloths can hold their breath longer than dolphins can - up to 40 minutes.", Category.ANIMALS),
        TriviaFact(50, "The word 'nerd' was first coined by Dr. Seuss in his 1950 book 'If I Ran the Zoo'.", Category.LANGUAGE)
    )
    
    fun getRandomFact(): TriviaFact {
        return triviaFacts.random()
    }
    
    fun getAllFacts(): List<TriviaFact> {
        return triviaFacts.shuffled()
    }
}
