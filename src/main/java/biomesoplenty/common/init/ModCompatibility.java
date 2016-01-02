package biomesoplenty.common.init;

import biomesoplenty.common.handler.loading.SwapManager;

public class ModCompatibility 
{
    public static void init()
    {
        setupBackwardsCompatibility();
    }
    
    //Currently setup for converting from 1.7.10 to 1.8.9
    private static void setupBackwardsCompatibility()
    {
        SwapManager swapManager = SwapManager.INSTANCE;
        
        //Replace old block ids
        swapManager.remapBlockId("petals", "leaves_5");
        
        swapManager.remapBlockId("newBopGrass", "grass");
        swapManager.remapBlockId("newBopDirt", "dirt");
        
        swapManager.remapBlockId("planks", "planks_0");
        
        swapManager.remapBlockId("logs1", "log_0");
        swapManager.remapBlockId("logs2", "log_1");
        swapManager.remapBlockId("logs3", "log_2");
        swapManager.remapBlockId("logs4", "log_3");
        
        swapManager.remapBlockId("leaves1", "leaves_0");
        swapManager.remapBlockId("leaves2", "leaves_1");
        swapManager.remapBlockId("leaves3", "leaves_2");
        swapManager.remapBlockId("leaves4", "leaves_3");
        
        swapManager.remapBlockId("sacredoakStairs", "sacred_oak_stairs");
        swapManager.remapBlockId("cherryStairs", "cherry_stairs");
        swapManager.remapBlockId("darkStairs", "dark_stairs");
        swapManager.remapBlockId("firStairs", "fir_stairs");
        swapManager.remapBlockId("etherealStairs", "ethereal_stairs");
        swapManager.remapBlockId("magicStairs", "magic_stairs");
        swapManager.remapBlockId("mangroveStairs", "mangrove_stairs");
        swapManager.remapBlockId("palmStairs", "palm_stairs");
        swapManager.remapBlockId("redwoodStairs", "redwood_stairs");
        swapManager.remapBlockId("willowStairs", "willow_stairs");
        swapManager.remapBlockId("pineStairs", "pine_stairs");
        swapManager.remapBlockId("hellBarkStairs", "hellbark_stairs");
        swapManager.remapBlockId("jacarandaStairs", "jacaranda_stairs");
        swapManager.remapBlockId("mahoganyStairs", "mahogany_stairs");
        
        swapManager.remapBlockId("woodenDoubleSlab1", "double_wood_slab_0");
        swapManager.remapBlockId("woodenDoubleSlab2", "double_wood_slab_1");
        
        swapManager.remapBlockId("stoneDoubleSlab", "double_other_slab");
        
        swapManager.remapBlockId("gemOre", "gem_ore");
        swapManager.remapBlockId("stoneFormations", "stone_formations");
        
        swapManager.remapBlockId("rocks", "stone");
        swapManager.remapBlockId("ash", "ash_block");
        swapManager.remapBlockId("ashStone", "ash_stone");
        swapManager.remapBlockId("mudBricks", "mud_brick_block");
        swapManager.remapBlockId("cragRock", "crag_rock");
        swapManager.remapBlockId("bones", "bone_segment");
        swapManager.remapBlockId("lilyBop", "waterlily");
        
        swapManager.remapBlockId("flowers,", "flower_0");
        swapManager.remapBlockId("flowers2", "flower_1");
        
        //Ignore
        //[21:18:24] [Server thread/ERROR]: Unidentified block: biomesoplenty:grave, id 223
        
        //Manual swap - FML doesn't like merging blocks
        //swapManager.swapBlockId("bopGrass", "grass");
        //[21:18:24] [Server thread/ERROR]: Unidentified block: biomesoplenty:colorizedLeaves1, id 246
        //[21:18:24] [Server thread/ERROR]: Unidentified block: biomesoplenty:colorizedLeaves2, id 247
        //[21:18:24] [Server thread/ERROR]: Unidentified block: biomesoplenty:originGrass, id 197
        //[21:18:24] [Server thread/ERROR]: Unidentified block: biomesoplenty:longGrass, id 198
        //[21:18:24] [Server thread/ERROR]: Unidentified block: biomesoplenty:overgrownNetherrack, id 199
        /*
         * 




[21:18:24] [Server thread/ERROR]: Unidentified block: biomesoplenty:hardDirt, id 215
[21:18:24] [Server thread/ERROR]: Unidentified block: biomesoplenty:mudBricksStairs, id 245

[21:18:24] [Server thread/ERROR]: Unidentified block: biomesoplenty:woodenSingleSlab2, id 227
[21:18:24] [Server thread/ERROR]: Unidentified block: biomesoplenty:woodenSingleSlab1, id 225

[21:18:24] [Server thread/ERROR]: Unidentified block: biomesoplenty:stoneSingleSlab, id 229
[21:18:24] [Server thread/ERROR]: Unidentified block: biomesoplenty:willow, id 181
[21:18:24] [Server thread/ERROR]: Unidentified block: biomesoplenty:plants, id 176
[21:18:24] [Server thread/ERROR]: Unidentified block: biomesoplenty:colorizedSaplings, id 213
[21:18:24] [Server thread/ERROR]: Unidentified block: biomesoplenty:persimmonLeaves, id 193
[21:18:24] [Server thread/ERROR]: Unidentified block: biomesoplenty:appleLeaves, id 192
[21:18:24] [Server thread/ERROR]: Unidentified block: biomesoplenty:driedDirt, id 166
[21:18:24] [Server thread/ERROR]: Unidentified block: biomesoplenty:foliage, id 186
[21:18:24] [Server thread/ERROR]: Unidentified block: biomesoplenty:mushrooms, id 180

[21:18:24] [Server thread/ERROR]: Unidentified block: biomesoplenty:honeyBlock, id 221
[21:18:24] [Server thread/ERROR]: Unidentified block: biomesoplenty:hell_blood, id 249
[21:18:24] [Server thread/ERROR]: Unidentified block: biomesoplenty:biomeBlock, id 216
[21:18:24] [Server thread/ERROR]: Unidentified block: biomesoplenty:coral2, id 189
[21:18:24] [Server thread/ERROR]: Unidentified block: biomesoplenty:coral1, id 188
[21:18:24] [Server thread/ERROR]: Unidentified block: biomesoplenty:saplings, id 212
[21:18:24] [Server thread/ERROR]: Unidentified block: biomesoplenty:willowStairs, id 240
[21:18:24] [Server thread/ERROR]: Unidentified block: biomesoplenty:flowerVine, id 184
[21:18:24] [Server thread/ERROR]: Unidentified block: biomesoplenty:hardIce, id 191
[21:18:24] [Server thread/ERROR]: Unidentified block: biomesoplenty:treeMoss, id 183
[21:18:24] [Server thread/ERROR]: Unidentified block: biomesoplenty:turnip, id 187
[21:18:24] [Server thread/ERROR]: Unidentified block: biomesoplenty:fruitBop, id 252
[21:18:24] [Server thread/ERROR]: Unidentified block: biomesoplenty:hardSand, id 214




[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:redwoodStairs, id 239
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:honey, id 250
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:newBopGrass, id 201
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:scytheStone, id 4123
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:ashStone, id 190
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:planks, id 224
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:mahoganyStairs, id 244
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:poison, id 248
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:newBopDirt, id 202
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:etherealStairs, id 235
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:bopBucket, id 4107
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:helmetAmethyst, id 4133
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:pineStairs, id 241
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:magicStairs, id 236
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:chestplateAmethyst, id 4134
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:flowerBand, id 4137
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:stoneFormations, id 179
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:bones, id 222
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:axeMud, id 4115
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:hardDirt, id 215
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:mudBricksStairs, id 245
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:originGrass, id 197
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:stoneSingleSlab, id 229
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:bootsMud, id 4132
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:plants, id 176
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:dartBlower, id 4103
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:mangroveStairs, id 237
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:leggingsAmethyst, id 4135
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:persimmonLeaves, id 193
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:ancientStaff, id 4105
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:chestplateMud, id 4130
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:driedDirt, id 166
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:food, id 4096
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:mushrooms, id 180
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:honeyBlock, id 221
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:scytheGold, id 4125
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:biomeBlock, id 216
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:coral2, id 189
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:coral1, id 188
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:firStairs, id 234
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:sacredoakStairs, id 231
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:shovelMud, id 4113
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:misc, id 4098
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:colorizedLeaves1, id 246
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:colorizedLeaves2, id 247
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:swordAmethyst, id 4117
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:jarEmpty, id 4101
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:scytheIron, id 4124
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:hellBarkStairs, id 242
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:fruitBop, id 252
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:bopGrass, id 200
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:turnipSeeds, id 4097
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:petals, id 211
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:stoneDoubleSlab, id 230
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:rocks, id 167
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:scytheAmethyst, id 4128
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:mudBricks, id 196
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:helmetMud, id 4129
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:wadingBoots, id 4139
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:shovelAmethyst, id 4118
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:gemOre, id 218
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:swordMud, id 4112
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:scytheMud, id 4127
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:biomeEssence, id 4111
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:hoeMud, id 4116
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:cragRock, id 219
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:woodenDoubleSlab2, id 228
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:woodenDoubleSlab1, id 226
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:leaves4, id 210
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:leaves2, id 208
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:leaves3, id 209
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:grave, id 223
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:lilyBop, id 253
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:leaves1, id 207
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:flowers, id 177
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:axeAmethyst, id 4120
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:scytheDiamond, id 4126
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:jarFilled, id 4102
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:woodenSingleSlab2, id 227
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:leggingsMud, id 4131
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:woodenSingleSlab1, id 225
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:longGrass, id 198
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:pickaxeMud, id 4114
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:willow, id 181
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:darkStairs, id 233
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:colorizedSaplings, id 213
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:appleLeaves, id 192
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:foliage, id 186
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:biomeFinder, id 4110
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:logs4, id 206
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:logs3, id 205
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:logs2, id 204
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:overgrownNetherrack, id 199
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:logs1, id 203
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:gems, id 4099
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:palmStairs, id 238
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:hell_blood, id 249
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:pickaxeAmethyst, id 4119
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:cherryStairs, id 232
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:saplings, id 212
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:jacarandaStairs, id 243
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:flowers2, id 178
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:willowStairs, id 240
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:flowerVine, id 184
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:hoeAmethyst, id 4121
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:hardIce, id 191
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:treeMoss, id 183
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:scytheWood, id 4122
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:bootsAmethyst, id 4136
[21:18:24] [Server thread/ERROR]: Unidentified item: biomesoplenty:hardSand, id 214
         */
    }
}
