package main;

public class Map {
		GameFrame tc;
		
		Map(GameFrame tc){
			this.tc = tc;
		}
	void homewall() {
		for (int i = 0; i < 10; i++) { // 家的格局(无需修改)
			if (i < 4) 
				tc.homeWall.add(new BrickWall(350, 580 - 21 * i , tc));//homewall无需修改
			else if (i < 7)
				tc.homeWall.add(new BrickWall(372 + 22 * (i - 4), 517 , tc));//homewall无需修改
			else
				tc.homeWall.add(new BrickWall(416, 538 + (i - 7) * 21 , tc));//homewall无需修改
		}
	}
		
	void brickwall(int level) {
		switch(level) {
		
		case 1:{
			for (int i = 0; i < 32; i++) { // 砖墙
				if (i < 16) {
					tc.otherWall.add(new BrickWall(220 + 20 * i,300 , tc)); // 砖墙布局  随机数
					tc.otherWall.add(new BrickWall(500 + 20 * i,180 , tc));
					tc.otherWall.add(new BrickWall(200, 400+ 20 * i , tc));
					tc.otherWall.add(new BrickWall(500, 400+ 20 * i , tc));
				} else if (i < 32) { 
					tc.otherWall.add(new BrickWall(220+ 20 * (i - 16), 320 , tc));
					tc.otherWall.add(new BrickWall(500+ 20 * (i - 16), 220 , tc));
					tc.otherWall.add(new BrickWall(220,400+ 20 * (i - 16) , tc));
					tc.otherWall.add(new BrickWall(520, 400+ 20 * (i - 16) , tc));
				}
			}
			 break;
		}
		
		case 2:{
			 for (int i = 0; i < 32; i++) {
                 if (i < 10) {

                     tc.otherWall.add(new BrickWall(275, 408 + 21 * i, tc));
                     tc.otherWall.add(new BrickWall(490, 408 + 21 * i, tc));

                 } else if (i < 32) {

                     tc.otherWall.add(new BrickWall(620, 120 + 21 * (i - 16), tc));
                 }
             }
			 break;
		}
		
		case 3:{
			 for (int i = 0; i < 32; i++) {
                 if (i < 10) {
                     tc.otherWall.add(new BrickWall(150, 408 + 21 * i, tc));
                     tc.otherWall.add(new BrickWall(575, 408 + 21 * i, tc));
                     tc.otherWall.add(new BrickWall(170 + 21 * i, 250, tc));
                     tc.otherWall.add(new BrickWall(280 + 21 * i, 460, tc));
                 }
             }
			 break;
		}
		case 4:{
			 for (int i = 0; i < 32; i++) {
	            if (i < 10) {
	                tc.otherWall.add(new BrickWall(150, 408 + 21 * i, tc));
	                tc.otherWall.add(new BrickWall(575, 408 + 21 * i, tc));
	                tc.otherWall.add(new BrickWall(170 + 21 * i, 250, tc));
	                tc.otherWall.add(new BrickWall(280 + 21 * i, 460, tc));
	            }
	        }
			 break;
		}
	}
		
	}
	void metalwall(int level) {
		
		switch(level) {
		
		case 1:{
			for (int i = 0; i < 20; i++) { // 金属墙布局
				if (i < 10) {
					tc.metalWall.add(new MetalWall(140 + 36 * i, 150 , tc));
					tc.metalWall.add(new MetalWall(600, 400 + 37 * (i), tc));
				} else if (i < 20)
					tc.metalWall.add(new MetalWall(140 + 36 * (i - 10), 187 , tc));
				else
					tc.metalWall.add(new MetalWall(500 + 36 * (i - 10), 160 , tc));
			}
			break;
		}
		
		case 2:{
			for (int i = 0; i < 20; i++) {
                if (i < 4) {
                    tc.metalWall.add(new MetalWall(220 + 30 * i, 360, tc));
                    tc.metalWall.add(new MetalWall(440 + 30 * i, 360, tc));
                    tc.metalWall.add(new MetalWall(560, 150 + 37 * i, tc));
                    tc.otherWall.add(new BrickWall(340, 240 + 21 * i, tc));
                    tc.otherWall.add(new BrickWall(430, 240 + 21 * i, tc));
                } else if (i < 20) {
                    tc.metalWall.add(new MetalWall(140 + 30 * (i - 10), 180, tc));
                }

            }
			break;
		}
		
		case 3:{
			for (int i = 0; i < 3; i++) {
                tc.metalWall.add(new MetalWall(400 + 37 * i, 100, tc));
               // metalWall.add(new main.MetalWall(400 + 30 * i, 190, this));
                tc.metalWall.add(new MetalWall(364, 100 + 37 * i, tc));
                tc.metalWall.add(new MetalWall(510, 100 + 37 * i, tc));
                tc.metalWall.add(new MetalWall(620 + 30 * i, 150, tc));
                tc.metalWall.add(new MetalWall(150 + 30 * i, 150, tc));
                tc.metalWall.add(new MetalWall(600 + 30 * i, 290, tc));
                tc.metalWall.add(new MetalWall(180 + 30 * i, 300, tc));
                tc.metalWall.add(new MetalWall(600, 430 + 37 * i, tc));
                tc.metalWall.add(new MetalWall(180, 430 + 37 * i, tc));
            }

			tc.metalWall.add(new MetalWall(364, 208, tc));
			tc.metalWall.add(new MetalWall(510, 208, tc));
			break;
		}
		
		case 4:{
			for (int i = 0; i < 3; i++) {
                tc.metalWall.add(new MetalWall(400 + 37 * i, 100, tc));
               // metalWall.add(new main.MetalWall(400 + 30 * i, 190, this));
                tc.metalWall.add(new MetalWall(364, 100 + 37 * i, tc));
                tc.metalWall.add(new MetalWall(510, 100 + 37 * i, tc));
                tc.metalWall.add(new MetalWall(620 + 30 * i, 150, tc));
                tc.metalWall.add(new MetalWall(150 + 30 * i, 150, tc));
                //tc.metalWall.add(new main.MetalWall(600 + 30 * i, 290, tc));
                //tc.metalWall.add(new main.MetalWall(180 + 30 * i, 300, tc));
                tc.metalWall.add(new MetalWall(600, 485 + 37 * i, tc));
                tc.metalWall.add(new MetalWall(180, 408 + 37 * i, tc));
            }
			break;
		}
		
		}
		
		
	}
	void tree(int level) {
		
		switch(level) {
		
		case 1:{
			for (int i = 0; i < 4; i++) { // 树的布局
				if (i < 4) {
					tc.trees.add(new Tree(0 + 30 * i, 360 , tc));
					tc.trees.add(new Tree(220 + 30 * i, 360 , tc));
					tc.trees.add(new Tree(440 + 30 * i, 360 , tc));
					tc.trees.add(new Tree(660 + 30 * i, 360 , tc));
				}
			}

				break;
			}
		
		
		case 2:{
			for (int i = 0; i < 4; i++) {
                if (i < 4) {
                    tc.trees.add(new Tree(0 + 30 * i, 360, tc));
                    tc.trees.add(new Tree(0 + 30 * i, 220, tc));
                    tc.trees.add(new Tree(660 + 30 * i, 300, tc));
                    tc.trees.add(new Tree(660 + 30 * i, 430, tc));
                }

            }

            break;
        }
		
		case 3:{
			for (int i = 0; i < 4; i++) {
                if (i < 4) {
                    tc.trees.add(new Tree(0 + 30 * i, 360, tc));
                    tc.trees.add(new Tree(0 + 30 * i, 220, tc));
                    tc.trees.add(new Tree(660 + 30 * i, 220, tc));
                    tc.trees.add(new Tree(660 + 30 * i, 360, tc));
                }

            }
			break;
		}
		case 4:{
			for (int i = 0; i < 4; i++) {
                if (i < 4) {
                    tc.trees.add(new Tree(0 + 30 * i, 360, tc));
                    tc.trees.add(new Tree(0 + 30 * i, 220, tc));
                    tc.trees.add(new Tree(660 + 30 * i, 220, tc));
                    tc.trees.add(new Tree(660 + 30 * i, 360, tc));
                }

            }
			break;
		}
	}
}
	void river(int level) {
		
		switch(level) {
		
		case 1:{
			tc.theRiver.add(new River(84, 100, tc));
			break;
		}
		
		case 2:{
			tc.theRiver.add(new River(660, 115, tc));
			break;
		}
		
		case 3:{
			for (int i = 0; i < 4; i++) {
                tc.theRiver.add(new River(280 + 55 * i, 280, tc));
            }
			break;
		}
		
		case 4:{
			for (int i = 0; i < 2; i++) {
                tc.theRiver.add(new River(220 + 100 * i, 280, tc));
            }
			for (int i = 2; i < 4; i++) {
                tc.theRiver.add(new River(280 + 107 * i, 230, tc));
            }
			break;
		}
		
		}
	}
}
	
