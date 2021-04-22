package com.example.generatefreebootersnpc

import android.os.Parcel
import android.os.Parcelable
import java.util.*

data class NonPlayerCharacter(var NPCname: String = "some name") : Parcelable {
    var prevailingAlignment: String? = "some prev alignment"
    var prevailingHeritage: String? = "some prev heritage"

    val heritage: String
        get() {
            return when (prevailingHeritage) {
                "elf" -> roll1d12(arrElfHeritage)
                "dwarf" -> roll1d12(arrDwarfHeritage)
                "human" -> roll1d12(arrHumanHeritage)
                "halfling" -> roll1d12(arrHalflingHeritage)
                else -> "something wrong"
            }
        }

    val alignment: String
        get() {
            return when (prevailingAlignment) {
                "good" -> roll1d12(arrGoodAlignment)
                "lawful" -> roll1d12(arrLawfulAlignment)
                "neutral" -> roll1d12(arrNeutralAlignment)
                "chaotic" -> roll1d12(arrChaoticAlignment)
                "evil" -> roll1d12(arrEvilAlignment)
                else -> "something wrong"
            }
        }

    val occupation: String
        get() {
            return when (roll1d12(arrOccupation)) {
                "Outsider" -> roll1d12(arrOutsiderOccupation)
                "Criminal" -> roll1d12(arrCriminalOccupation)
                "Commoner" -> roll1d12(arrCommonerOccupation)
                "Tradesperson" -> roll1d12(arrTradespersonOccupation)
                "Merchant" -> roll1d12(arrMerchantOccupation)
                "Specialist" -> roll1d12(arrSpecialistOccupation)
                "Religious" -> roll1d12(arrReligiousOccupation)
                "Security" -> roll1d12(arrSecurityOccupation)
                "Authority" -> roll1d12(arrAuthorityOccupation)
                else -> "we don't know who you are"
            }
        }

    var motivation: String? = "some motivation"
        get() {
            return when (alignment) {
                "good" -> roll1d12(arrMotivationGood)
                "lawful" -> roll1d12(arrMotivationLawful)
                "neutral" -> roll1d12(arrMotivationNeutral)
                "chaotic" -> roll1d12(arrMotivationChaotic)
                "evil" -> roll1d12(arrMotivationEvil)
                else -> "something wrong"
            }
        }


    var virtues: Int = 0
        get() {
            return when (alignment) {
                "good" -> 3
                "lawful" -> 2
                "neutral" -> 1
                "chaotic" -> 1
                "evil" -> 0
                else -> 0
            }
        }

    var vices: Int = 0
        get() {
            return when (alignment) {
                "good" -> 0
                "lawful" -> 1
                "neutral" -> 1
                "chaotic" -> 2
                "evil" -> 3
                else -> 0
            }
        }

    var virtue: String? = "some virtue"
        get() {
            return rollTraits(virtues, arrVirtues)
        }

    var vice: String? = "some vice"
        get() {
            return rollTraits(vices, arrVices)
        }


    //var NPCname: String? = "some name"

    val gender: String
        get() {
            var random1d100 = (0..100).random()
            return when (random1d100) {
                11, 22, 33, 44, 55, 66, 77, 88, 99 -> "nonbinary/androgynous"
                in 0..100 step 2 -> "female"
                in 1..99 step 2 -> "male"
                else -> "something wrong"
            }
        }

    fun roll1d12(arrayString: Array<String>): String {
        var random = Random();
        var result = random.nextInt(arrayString.count())
        return arrayString[result]
    }

    fun rollTraits(int: Int, arr: Array<String>): String {
        var resultTraits = mutableListOf<String>()
        var random = Random();
        if (int == 0) return "-"
        else {
            while (resultTraits.count() < int) {
                var randomTrait = arr[random.nextInt(arr.count())]
                if (randomTrait !in resultTraits) {
                    resultTraits.add(randomTrait)
                }
            }
        }
        return resultTraits.toString().replace("[", "").replace("]", "")
    }

    // OCCUPATION
    private val arrOccupation = arrayOf(
        "Outsider",
        "Criminal",
        "Commoner",
        "Commoner",
        "Commoner",
        "Tradesperson",
        "Tradesperson",
        "Merchant",
        "Specialist",
        "Religious",
        "Security",
        "Authority"
    )

    private val arrOutsiderOccupation = arrayOf(
        "hermit/prophet",
        "fugitive/outlaw/exile",
        "fugitive/outlaw/exile",
        "barbarian",
        "barbarian",
        "beggar/vagrant/refugee",
        "beggar/vagrant/refugee",
        "herder/hunter/trapper",
        "herder/hunter/trapper",
        "diplomat/envoy",
        "rare humanoid",
        "otherworldly/arcane"
    )
    private val arrCriminalOccupation = arrayOf(
        "bandit/brigand/thug",
        "bandit/brigand/thug",
        "cutpurse/thief",
        "cutpurse/thief",
        "bodyguard/tough",
        "bodyguard/tough",
        "burglar",
        "con artist/swindler",
        "dealer/fence",
        "racketeer",
        "lieutenant",
        "boss/kingpin"
    )
    private val arrCommonerOccupation = arrayOf(
        "layabout/simpleton",
        "beggar/urchin",
        "beggar/urchin",
        "child",
        "child",
        "housewife/husband",
        "farmer/herder/hunter",
        "farmer/herder/hunter",
        "laborer/servant",
        "driver/porter/guide",
        "sailor/guard",
        "apprentice/adventurer"
    )
    private val arrTradespersonOccupation = arrayOf(
        "musician/troubador",
        "artist/actor/acrobat",
        "cobbler/furrier/tailor",
        "weaver/basketmaker",
        "potter/carpenter",
        "mason/baker/chandler",
        "cooper/wheelwright",
        "tanner/ropemaker",
        "stablekeeper/herbalist",
        "vintner/jeweler",
        "inkeep/tavernkeep",
        "smith/armorer"
    )
    private val arrMerchantOccupation = arrayOf(
        "raw materials/supplies",
        "raw materials/supplies",
        "general goods/outfitter",
        "general goods/outfitter",
        "grain/livestock",
        "ale/wine/spirits",
        "clothing/jewelry",
        "weapons/armor",
        "spices/tobacco",
        "labor/slaves",
        "books/scrolls",
        "magic supplies/items"
    )
    private val arrSpecialistOccupation = arrayOf(
        "clerk/scribe",
        "undertaker",
        "perfumer",
        "navigator/guide",
        "spy/diplomat",
        "cartographer",
        "locksmith/tinker",
        "architect/engineer",
        "physician/apothecary",
        "sage/scholar",
        "alchemist/astrologer",
        "inventor/wizard"
    )
    private val arrReligiousOccupation = arrayOf(
        "heretic/apostate",
        "zealot",
        "mendicant/pilgrim",
        "mendicant/pilgrim",
        "monk/nun/cultist",
        "monk/nun/cultist",
        "preacher/prophet",
        "missionary",
        "templar/protector",
        "priest/cult leader",
        "priest/cult leader",
        "high priest"
    )
    private val arrSecurityOccupation = arrayOf(
        "militia",
        "militia",
        "scout/warden",
        "watch/patrol",
        "watch/patrol",
        "raw recruit",
        "foot soldier",
        "foot soldier",
        "archer",
        "officer/constable",
        "cavalry/knight",
        "hero/general"
    )
    private val arrAuthorityOccupation = arrayOf(
        "courier/messenger",
        "town crier",
        "tax collector",
        "clerk/administrator",
        "clerk/administrator",
        "armiger/gentry",
        "armiger/gentry",
        "magistrate/judge",
        "guildmaster",
        "lesser nobility",
        "greater nobility",
        "ruler/warlord"
    )

    //ALIGNMENT
    private val arrLawfulAlignment = arrayOf(
        "evil",
        "evil",
        "chaotic",
        "neutral",
        "neutral",
        "lawful",
        "lawful",
        "lawful",
        "lawful",
        "lawful",
        "good",
        "good"
    )
    private val arrNeutralAlignment = arrayOf(
        "evil",
        "chaotic",
        "chaotic",
        "neutral",
        "neutral",
        "neutral",
        "neutral",
        "neutral",
        "neutral",
        "lawful",
        "lawful",
        "good"
    )
    private val arrChaoticAlignment = arrayOf(
        "evil",
        "evil",
        "chaotic",
        "chaotic",
        "chaotic",
        "chaotic",
        "chaotic",
        "chaotic",
        "chaotic",
        "lawful",
        "good",
        "good"
    )
    private val arrEvilAlignment = arrayOf(
        "evil",
        "evil",
        "evil",
        "evil",
        "evil",
        "chaotic",
        "chaotic",
        "neutral",
        "neutral",
        "lawful",
        "lawful",
        "good"
    )
    private val arrGoodAlignment = arrayOf(
        "evil",
        "chaotic",
        "chaotic",
        "neutral",
        "neutral",
        "lawful",
        "lawful",
        "good",
        "good",
        "good",
        "good",
        "good"
    )

    //HERITAGE
    private val arrHumanHeritage = arrayOf(
        "human",
        "human",
        "human",
        "human",
        "human",
        "human",
        "human",
        "halfling",
        "halfling",
        "dwarf",
        "dwarf",
        "elf"
    )
    private val arrElfHeritage = arrayOf(
        "elf",
        "elf",
        "elf",
        "elf",
        "elf",
        "elf",
        "elf",
        "elf",
        "elf",
        "human",
        "halfling",
        "dwarf"
    )
    private val arrHalflingHeritage = arrayOf(
        "halfling",
        "halfling",
        "halfling",
        "halfling",
        "halfling",
        "halfling",
        "halfling",
        "human",
        "human",
        "dwarf",
        "dwarf",
        "elf"
    )
    private val arrDwarfHeritage = arrayOf(
        "dwarf",
        "dwarf",
        "dwarf",
        "dwarf",
        "dwarf",
        "dwarf",
        "dwarf",
        "human",
        "human",
        "halfling",
        "halfling",
        "elf"
    )

    //MOTIVATION
    private val arrMotivationGood = arrayOf(
        "empathy",
        "empathy",
        "charity",
        "charity",
        "valor",
        "valor",
        "trust",
        "trust",
        "cooperation",
        "cooperation",
        "love",
        "love"
    )
    private val arrMotivationLawful = arrayOf(
        "truth",
        "truth",
        "justice",
        "justice",
        "discipline",
        "discipline",
        "loyalty",
        "loyalty",
        "order",
        "order",
        "honor",
        "honor"
    )
    private val arrMotivationNeutral = arrayOf(
        "knowledge",
        "knowledge",
        "balance",
        "balance",
        "fame",
        "fame",
        "advancement",
        "advancement",
        "investment",
        "investment",
        "luck",
        "luck"
    )
    private val arrMotivationChaotic = arrayOf(
        "satisfaction",
        "satisfaction",
        "vengeance",
        "vengeance",
        "impulse",
        "impulse",
        "celebration",
        "celebration",
        "disruption",
        "disruption",
        "passion",
        "passion"
    )
    private val arrMotivationEvil = arrayOf(
        "corruption",
        "corruption",
        "control",
        "control",
        "infamy",
        "infamy",
        "greed",
        "greed",
        "power",
        "power",
        "hatred",
        "hatred"
    )

    //VICES AND VIRTUES
    private val arrVices = arrayOf(
        "mad",
        "addict",
        "liar",
        "aggressive",
        "lustful",
        "alcoholic",
        "antagonistic",
        "malicious",
        "arrogant",
        "manipulative",
        "boastful",
        "merciless",
        "cheater",
        "moody",
        "covetous",
        "murderous",
        "cowardly",
        "obsessive",
        "cruel",
        "petulant",
        "decadent",
        "prejudiced",
        "deceitful",
        "reckless",
        "disloyal",
        "resentful",
        "doubtful",
        "rude",
        "egotistical",
        "ruthless",
        "envious",
        "self-pitying",
        "gluttonous",
        "selfish",
        "greedy",
        "snobbish",
        "hasty",
        "stingy",
        "hedonist",
        "stubborn",
        "impatient",
        "vain",
        "inflexible",
        "vengeful",
        "irritable",
        "wasteful",
        "lazy",
        "wrathful",
        "lewd",
        "zealous"
    )
    private val arrVirtues = arrayOf(
        "ambitious",
        "funny",
        "benevolent",
        "generous",
        "bold",
        "gregarious",
        "brave",
        "helpful",
        "charitable",
        "honest",
        "chaste",
        "honorable",
        "cautious",
        "hopeful",
        "compassionate",
        "humble",
        "confident",
        "idealistic",
        "considerate",
        "just",
        "cooperative",
        "kind",
        "courteous",
        "loving",
        "creative",
        "loyal",
        "curious",
        "merciful",
        "daring",
        "orderly",
        "defiant",
        "patient",
        "dependable",
        "persistent",
        "determined",
        "pious",
        "disciplined",
        "resourceful",
        "enthusiastic",
        "respectful",
        "fair",
        "responsible",
        "focused",
        "selfless",
        "forgiving",
        "steadfast",
        "friendly",
        "tactful",
        "frugal",
        "tolerant"
    )

    constructor(parcel: Parcel) : this(parcel.readString().toString()) {
        prevailingAlignment = parcel.readString()
        prevailingHeritage = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(NPCname)
        parcel.writeString(prevailingAlignment)
        parcel.writeString(prevailingHeritage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NonPlayerCharacter> {
        override fun createFromParcel(parcel: Parcel): NonPlayerCharacter {
            return NonPlayerCharacter(parcel)
        }

        override fun newArray(size: Int): Array<NonPlayerCharacter?> {
            return arrayOfNulls(size)
        }
    }
}