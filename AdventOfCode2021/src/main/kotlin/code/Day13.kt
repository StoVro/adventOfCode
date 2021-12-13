package code

fun main() {
    day13FirstPuzzle()
    day13SecondPuzzle()
}

fun day13FirstPuzzle() {
    val foldedList = INPUT_DAY13_COORDINATES.foldVerticalBeforeIndex(655)
    val distinctNumbers = foldedList.getNumberOfDistinctPoints()
    println(distinctNumbers)
}

fun day13SecondPuzzle() {
    val foldList = listOf(
            Pair("fold along x",655),
            Pair("fold along y",447),
            Pair("fold along x",327),
            Pair("fold along y",223),
            Pair("fold along x",163),
            Pair("fold along y",111),
            Pair("fold along x",81),
            Pair("fold along y",55),
            Pair("fold along x",40),
            Pair("fold along y",27),
            Pair("fold along y",13),
            Pair("fold along y",6))

    var listPoints = INPUT_DAY13_COORDINATES

    foldList.forEach {
        listPoints = if(it.first.endsWith("x")) {
            listPoints.foldVerticalBeforeIndex(it.second)
        } else {
            listPoints.foldHorizontalBeforeIndex(it.second)
        }
    }

    listPoints.printCode()
}

fun List<Point>.printCode() {
    val xMaximum = this.map { it.x }.maxByOrNull { it }!!
    val yMaximum = this.map { it.y }.maxByOrNull { it }!!

    repeat(yMaximum.plus(1)) { y ->
        repeat(xMaximum.plus(1)) { x ->
               if(this.hasPoint(x, y)) {
                   print("##")
               } else {
                   print("..")
               }
        }
        println()
    }
}

fun List<Point>.hasPoint(x: Int, y: Int) : Boolean {
    return this.any { it.x == x && it.y == y}
}

fun List<Point>.foldVerticalBeforeIndex(index: Int) : List<Point> {
    val neueListe = mutableListOf<Point>()
    this.forEach { point ->
        if(point.x > (index-1)) {
            val newX = 2 * index - point.x
            neueListe.add(Point(newX, point.y))
        } else {
            neueListe.add(Point(point.x, point.y))
        }
    }
    return neueListe
}

fun List<Point>.foldHorizontalBeforeIndex(index: Int) : List<Point> {
    val neueListe = mutableListOf<Point>()
    this.forEach { point ->
        if(point.y > (index-1)) {
            val newY = 2 * index - point.y
            neueListe.add(Point(point.x, newY))
        } else {
            neueListe.add(Point(point.x, point.y))
        }
    }
    return neueListe
}

fun List<Point>.getNumberOfDistinctPoints() : Int{
    return this.groupingBy { Pair(it.x, it.y) }.eachCount().count()
}

data class Point(val x: Int, val y: Int)

val INPUT_DAY13_COORDINATES : List<Point>
    get() = listOf(Point(103, 224), Point(624, 491), Point(808, 688), Point(1076, 130), Point(700, 26), Point(55, 794), Point(119, 724), Point(773, 809), Point(875, 33), Point(922, 135), Point(509, 260), Point(801, 176), Point(1143, 85), Point(619, 526), Point(1250, 138), Point(753, 431), Point(1260, 654), Point(276, 457), Point(637, 718), Point(1183, 115), Point(284, 137), Point(539, 757), Point(279, 85), Point(1128, 474), Point(406, 469), Point(1086, 170), Point(927, 673), Point(310, 702), Point(35, 796), Point(268, 892), Point(202, 249), Point(820, 878), Point(992, 121), Point(339, 649), Point(1275, 796), Point(1113, 290), Point(336, 616), Point(522, 471), Point(755, 764), Point(631, 204), Point(241, 393), Point(455, 546), Point(1165, 176), Point(304, 276), Point(855, 859), Point(582, 380), Point(437, 9), Point(944, 400), Point(199, 770), Point(124, 473), Point(522, 423), Point(167, 85), Point(606, 493), Point(1158, 688), Point(1186, 473), Point(981, 54), Point(790, 507), Point(704, 493), Point(788, 362), Point(971, 201), Point(512, 889), Point(758, 810), Point(1101, 770), Point(325, 840), Point(734, 873), Point(725, 586), Point(416, 105), Point(1183, 365), Point(1233, 725), Point(698, 256), Point(418, 112), Point(520, 507), Point(276, 773), Point(634, 185), Point(483, 693), Point(612, 256), Point(651, 603), Point(1225, 53), Point(1268, 759), Point(35, 339), Point(311, 474), Point(984, 592), Point(1237, 770), Point(1093, 129), Point(755, 639), Point(671, 278), Point(788, 471), Point(430, 810), Point(693, 231), Point(281, 227), Point(454, 178), Point(1, 54), Point(1290, 499), Point(619, 407), Point(1081, 575), Point(1252, 266), Point(1186, 421), Point(698, 638), Point(1218, 561), Point(954, 763), Point(692, 402), Point(923, 532), Point(281, 631), Point(222, 777), Point(1047, 316), Point(1109, 215), Point(513, 824), Point(771, 645), Point(802, 640), Point(769, 471), Point(42, 256), Point(619, 80), Point(913, 425), Point(340, 68), Point(397, 833), Point(539, 137), Point(1136, 654), Point(515, 18), Point(507, 247), Point(1274, 402), Point(970, 364), Point(913, 362), Point(328, 410), Point(775, 404), Point(164, 16), Point(356, 763), Point(545, 353), Point(790, 506), Point(780, 46), Point(378, 569), Point(276, 437), Point(878, 777), Point(159, 613), Point(313, 137), Point(377, 418), Point(134, 22), Point(483, 14), Point(1207, 670), Point(1238, 738), Point(528, 268), Point(913, 761), Point(1009, 501), Point(1217, 805), Point(865, 361), Point(460, 801), Point(358, 715), Point(857, 775), Point(552, 842), Point(771, 197), Point(627, 40), Point(765, 577), Point(1071, 704), Point(862, 638), Point(811, 686), Point(219, 413), Point(904, 425), Point(728, 738), Point(1020, 588), Point(1240, 225), Point(619, 78), Point(611, 180), Point(610, 474), Point(388, 135), Point(957, 582), Point(194, 43), Point(1027, 842), Point(917, 864), Point(537, 770), Point(831, 26), Point(1240, 669), Point(1034, 582), Point(1210, 49), Point(1036, 282), Point(1217, 537), Point(271, 100), Point(537, 691), Point(584, 290), Point(224, 618), Point(1111, 546), Point(610, 26), Point(726, 221), Point(1053, 509), Point(1295, 278), Point(1094, 859), Point(725, 245), Point(552, 52), Point(981, 168), Point(448, 190), Point(905, 64), Point(700, 126), Point(132, 179), Point(930, 648), Point(692, 189), Point(659, 291), Point(1027, 852), Point(984, 78), Point(318, 121), Point(1026, 757), Point(889, 385), Point(184, 596), Point(746, 50), Point(850, 93), Point(233, 173), Point(234, 130), Point(201, 679), Point(290, 588), Point(510, 0), Point(284, 869), Point(939, 628), Point(147, 303), Point(1265, 78), Point(445, 361), Point(763, 477), Point(1250, 586), Point(1178, 715), Point(282, 196), Point(1290, 617), Point(719, 191), Point(460, 93), Point(932, 325), Point(529, 343), Point(112, 829), Point(967, 567), Point(43, 714), Point(545, 541), Point(541, 423), Point(612, 759), Point(1262, 196), Point(473, 522), Point(878, 117), Point(512, 596), Point(182, 446), Point(783, 529), Point(797, 70), Point(768, 65), Point(477, 270), Point(552, 810), Point(373, 227), Point(502, 682), Point(378, 121), Point(430, 724), Point(592, 565), Point(180, 254), Point(145, 718), Point(184, 298), Point(748, 354), Point(127, 147), Point(845, 400), Point(951, 485), Point(393, 260), Point(898, 357), Point(480, 667), Point(541, 871), Point(507, 199), Point(1267, 714), Point(407, 406), Point(288, 494), Point(152, 654), Point(827, 14), Point(1028, 698), Point(1000, 192), Point(549, 169), Point(490, 211), Point(1111, 592), Point(535, 404), Point(26, 854), Point(372, 126), Point(455, 859), Point(236, 340), Point(716, 808), Point(387, 532), Point(1101, 361), Point(857, 119), Point(999, 420), Point(276, 878), Point(373, 417), Point(923, 670), Point(92, 729), Point(179, 703), Point(619, 555), Point(455, 348), Point(668, 266), Point(674, 217), Point(1139, 880), Point(442, 530), Point(1042, 2), Point(594, 586), Point(890, 354), Point(13, 757), Point(698, 759), Point(268, 677), Point(1125, 276), Point(314, 826), Point(1210, 889), Point(207, 42), Point(691, 78), Point(241, 277), Point(42, 638), Point(176, 82), Point(445, 891), Point(315, 649), Point(1081, 95), Point(880, 810), Point(782, 268), Point(472, 229), Point(2, 826), Point(769, 23), Point(974, 616), Point(898, 537), Point(102, 674), Point(604, 374), Point(415, 480), Point(1154, 453), Point(1110, 14), Point(798, 695), Point(562, 333), Point(336, 809), Point(254, 508), Point(412, 313), Point(271, 221), Point(763, 171), Point(883, 255), Point(992, 773), Point(999, 327), Point(818, 36), Point(1081, 215), Point(974, 278), Point(1274, 588), Point(808, 234), Point(373, 477), Point(43, 42), Point(552, 674), Point(609, 816), Point(440, 486), Point(1081, 679), Point(105, 723), Point(281, 667), Point(865, 96), Point(574, 442), Point(290, 140), Point(1289, 294), Point(338, 682), Point(299, 264), Point(1268, 638), Point(1154, 5), Point(42, 759), Point(999, 474), Point(502, 688), Point(35, 787), Point(452, 400), Point(1116, 679), Point(571, 684), Point(420, 781), Point(1056, 396), Point(1309, 467), Point(1238, 193), Point(1287, 204), Point(448, 638), Point(418, 844), Point(832, 82), Point(1053, 385), Point(1290, 395), Point(1101, 768), Point(1027, 42), Point(1146, 858), Point(768, 787), Point(716, 674), Point(765, 541), Point(504, 82), Point(512, 471), Point(1206, 565), Point(691, 80), Point(391, 764), Point(1208, 514), Point(1, 427), Point(23, 690), Point(994, 212), Point(837, 522), Point(1265, 750), Point(209, 320), Point(952, 715), Point(999, 687), Point(329, 726), Point(1233, 85), Point(126, 626), Point(117, 462), Point(803, 143), Point(652, 712), Point(142, 298), Point(582, 50), Point(370, 309), Point(20, 838), Point(903, 488), Point(380, 515), Point(681, 536), Point(1128, 446), Point(291, 75), Point(502, 206), Point(70, 673), Point(233, 721), Point(127, 81), Point(99, 255), Point(765, 765), Point(102, 871), Point(388, 51), Point(612, 135), Point(763, 723), Point(387, 425), Point(639, 278), Point(1191, 180), Point(728, 514), Point(607, 869), Point(199, 302), Point(699, 282), Point(420, 354), Point(823, 64), Point(527, 747), Point(405, 64), Point(564, 50), Point(35, 526), Point(524, 334), Point(913, 61), Point(542, 667), Point(582, 722), Point(492, 36), Point(765, 353), Point(1074, 340), Point(840, 450), Point(875, 400), Point(937, 477), Point(2, 264), Point(1146, 260), Point(1027, 714), Point(1101, 320), Point(182, 474), Point(801, 767), Point(1260, 381), Point(72, 253), Point(195, 73), Point(390, 497), Point(1056, 508), Point(107, 275), Point(753, 504), Point(699, 180), Point(890, 540), Point(894, 105), Point(509, 270), Point(530, 249), Point(378, 94), Point(50, 65), Point(870, 486), Point(1029, 667), Point(728, 840), Point(974, 809), Point(803, 751), Point(92, 354), Point(749, 509), Point(1198, 206), Point(144, 0), Point(290, 208), Point(1195, 249), Point(470, 647), Point(1233, 809), Point(801, 270), Point(874, 793), Point(903, 406), Point(310, 192), Point(1109, 551), Point(806, 812), Point(1069, 841), Point(1161, 158), Point(1044, 311), Point(1168, 23), Point(542, 227), Point(1268, 361), Point(1166, 446), Point(421, 385), Point(782, 432), Point(478, 82), Point(1034, 885), Point(1154, 217), Point(216, 859), Point(677, 497), Point(372, 660), Point(806, 82), Point(612, 704), Point(483, 201), Point(820, 260), Point(933, 418), Point(873, 885), Point(744, 789), Point(147, 143), Point(594, 220), Point(45, 144), Point(1268, 135), Point(699, 119), Point(383, 221), Point(27, 854), Point(328, 352), Point(499, 880), Point(1298, 75), Point(437, 317), Point(102, 52), Point(1184, 432), Point(996, 826), Point(448, 25), Point(1211, 764), Point(987, 165), Point(913, 133), Point(35, 595), Point(271, 436), Point(1036, 155), Point(1255, 624), Point(1287, 690), Point(1298, 714), Point(492, 688), Point(827, 693), Point(1129, 521), Point(1028, 196), Point(1176, 388), Point(654, 190), Point(967, 106), Point(340, 364), Point(201, 215), Point(59, 670), Point(495, 606), Point(768, 227), Point(576, 873), Point(746, 844), Point(618, 82), Point(1238, 253), Point(387, 84), Point(50, 325), Point(631, 690), Point(705, 418), Point(937, 711), Point(1207, 152), Point(152, 682), Point(768, 829), Point(528, 686), Point(716, 220), Point(1183, 141), Point(277, 866), Point(35, 487), Point(44, 749), Point(798, 889), Point(705, 476), Point(326, 816), Point(1251, 224), Point(1310, 381), Point(1109, 343), Point(1211, 255), Point(60, 138), Point(1081, 103), Point(659, 603), Point(214, 65), Point(835, 703), Point(1290, 838), Point(534, 645), Point(539, 249), Point(1074, 50), Point(406, 425), Point(209, 768), Point(652, 182), Point(512, 838), Point(316, 212), Point(820, 16), Point(35, 147), Point(985, 691), Point(604, 65), Point(407, 488), Point(811, 880), Point(1193, 432), Point(937, 675), Point(344, 416), Point(400, 325), Point(1145, 168), Point(868, 530), Point(769, 871), Point(1298, 404), Point(134, 388), Point(1039, 436), Point(1033, 812), Point(681, 393), Point(517, 337), Point(582, 844), Point(725, 201), Point(1012, 187), Point(480, 675), Point(654, 704), Point(1275, 144), Point(115, 249), Point(12, 588), Point(1028, 644), Point(306, 220), Point(326, 592), Point(671, 431), Point(97, 431), Point(1146, 16), Point(380, 648), Point(955, 686), Point(604, 325), Point(119, 842), Point(1086, 618), Point(1309, 54), Point(562, 540), Point(1146, 464), Point(996, 523), Point(311, 327), Point(758, 471), Point(92, 281), Point(671, 655), Point(550, 565), Point(125, 103), Point(699, 404), Point(228, 752), Point(1298, 371), Point(576, 649), Point(552, 380), Point(1260, 513), Point(821, 427), Point(783, 141), Point(0, 78), Point(683, 40), Point(806, 826), Point(552, 276), Point(371, 567), Point(431, 0), Point(267, 532), Point(1086, 808), Point(336, 278), Point(372, 234), Point(530, 46), Point(58, 628), Point(12, 371), Point(855, 546), Point(282, 644), Point(938, 660), Point(1176, 22), Point(995, 649), Point(49, 476), Point(1238, 514), Point(1279, 485), Point(539, 697), Point(50, 513), Point(1190, 256), Point(490, 235), Point(430, 84), Point(18, 217), Point(453, 215), Point(1103, 42), Point(274, 164), Point(242, 537), Point(884, 752), Point(550, 329), Point(855, 299), Point(100, 845), Point(328, 526), Point(910, 514), Point(933, 476), Point(100, 49), Point(2, 378), Point(301, 501), Point(1298, 180), Point(939, 842), Point(470, 298), Point(35, 528), Point(746, 498), Point(156, 453), Point(857, 439), Point(564, 554), Point(144, 777), Point(1165, 718), Point(840, 695), Point(546, 793), Point(749, 385), Point(576, 201), Point(529, 103), Point(835, 751), Point(1266, 26), Point(1093, 577), Point(207, 852), Point(1297, 757), Point(933, 866), Point(841, 724), Point(1201, 129), Point(199, 859), Point(1074, 560), Point(335, 649), Point(271, 794), Point(1125, 724), Point(838, 229), Point(1183, 779), Point(782, 686), Point(413, 393), Point(765, 129), Point(77, 725), Point(1146, 430), Point(59, 224), Point(870, 107), Point(656, 704), Point(164, 270), Point(865, 320), Point(181, 521), Point(1063, 40), Point(229, 95), Point(142, 392), Point(987, 515), Point(838, 644), Point(276, 9), Point(103, 670), Point(790, 58), Point(174, 240), Point(1298, 819), Point(1131, 703), Point(435, 861), Point(1308, 712), Point(782, 626), Point(806, 147), Point(523, 261), Point(979, 379), Point(1026, 137), Point(591, 191), Point(726, 540), Point(92, 561), Point(400, 392), Point(1309, 427), Point(728, 380), Point(937, 417), Point(171, 880), Point(1308, 182), Point(1218, 540), Point(311, 119), Point(104, 565), Point(201, 343), Point(266, 583), Point(363, 371), Point(397, 89), Point(119, 714), Point(919, 130), Point(142, 502), Point(773, 691), Point(995, 245), Point(1265, 816), Point(748, 540), Point(1185, 103), Point(1072, 789), Point(185, 170), Point(1020, 754), Point(946, 626), Point(677, 600), Point(93, 805), Point(1081, 791), Point(633, 294), Point(512, 840), Point(728, 50), Point(771, 137), Point(520, 506), Point(897, 393), Point(1154, 565), Point(541, 471), Point(1297, 137), Point(967, 474), Point(499, 208), Point(639, 879), Point(318, 269), Point(629, 281), Point(975, 649), Point(490, 260), Point(545, 577), Point(156, 5), Point(383, 669), Point(913, 532), Point(119, 164), Point(162, 688), Point(1092, 793))
