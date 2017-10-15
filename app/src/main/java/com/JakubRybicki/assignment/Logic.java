/**
 * Jakub Rybicki
 * 2015 - 10 - 16
 * version 1.0
 */
package com.JakubRybicki.assignment;

import android.content.SharedPreferences;
import android.util.Log;

public class Logic {
	public static boolean isGameWon(int pos, Item[] gridArray, boolean isHardmode){
		byte z=0;
		byte maxNo=16;
		if(isHardmode)
			maxNo=20;
		for(int i=0;i<maxNo;i++){
			if(gridArray[i].getRDC()!=Item.color3)
				z++;
		}
		if(isHardmode)
			return (z==20);
		return (z==16);
	}
	public static boolean isGameLost(int pos, Item[] gridArray, boolean isHardMode){
    	final Item ITEM=gridArray[pos];
    	final int ROW=gridArray[pos].rowId;
    	final int COLUMN=gridArray[pos].columnId;
    	//Log.i("BTNS",COLUMN+" c:r "+ROW);
    	// ---------------------------- CHECK HORIZONTAL -----------------------------------------------
    	switch(ROW){
    	case 0:
    		if(COLUMN==0){
    			if (ITEM.getRDC()==gridArray[1].getRDC() && ITEM.getRDC()==gridArray[2].getRDC())
    					return true;
    		}else if(COLUMN==1){
    			if (ITEM.getRDC()==gridArray[0].getRDC()&&ITEM.getRDC()==gridArray[2].getRDC())
					return true;
    			else if(ITEM.getRDC()==gridArray[2].getRDC()&&ITEM.getRDC()==gridArray[3].getRDC())
    				return true;
    		}else if(COLUMN==2){
    			if (ITEM.getRDC()==gridArray[0].getRDC()&&ITEM.getRDC()==gridArray[1].getRDC())
    				return true;
    			if (ITEM.getRDC()==gridArray[1].getRDC()&&ITEM.getRDC()==gridArray[3].getRDC())
    				return true;
    		}else if(COLUMN==3){
    			if (ITEM.getRDC()==gridArray[1].getRDC() && ITEM.getRDC()==gridArray[2].getRDC())
					return true;
    		}
    		break;
    	case 1:
    		if(COLUMN==0){
    			if (ITEM.getRDC()==gridArray[5].getRDC() && ITEM.getRDC()==gridArray[6].getRDC())
					return true;
    		}else if(COLUMN==1){
    			if (ITEM.getRDC()==gridArray[4].getRDC()&&ITEM.getRDC()==gridArray[6].getRDC())
					return true;
    			else if(ITEM.getRDC()==gridArray[6].getRDC()&&ITEM.getRDC()==gridArray[7].getRDC())
    				return true;
    		}else if(COLUMN==2){
    			if (ITEM.getRDC()==gridArray[4].getRDC()&&ITEM.getRDC()==gridArray[5].getRDC())
    				return true;
    			if (ITEM.getRDC()==gridArray[5].getRDC()&&ITEM.getRDC()==gridArray[7].getRDC())
    				return true;
    		}else if(COLUMN==3){
    			if (ITEM.getRDC()==gridArray[5].getRDC() && ITEM.getRDC()==gridArray[6].getRDC())
					return true;
    		}
    		break;
    	case 2:
    		if(COLUMN==0){
    			if (ITEM.getRDC()==gridArray[9].getRDC() && ITEM.getRDC()==gridArray[10].getRDC())
					return true;
    		}else if(COLUMN==1){
    			if (ITEM.getRDC()==gridArray[10].getRDC()&&ITEM.getRDC()==gridArray[11].getRDC())
					return true;
    			else if(ITEM.getRDC()==gridArray[8].getRDC()&&ITEM.getRDC()==gridArray[10].getRDC())
    				return true;
    		}else if(COLUMN==2){
    			if (ITEM.getRDC()==gridArray[9].getRDC()&&ITEM.getRDC()==gridArray[8].getRDC())
    				return true;
    			if (ITEM.getRDC()==gridArray[9].getRDC()&&ITEM.getRDC()==gridArray[11].getRDC())
    				return true;
    		}else if(COLUMN==3){
    			if (ITEM.getRDC()==gridArray[9].getRDC() && ITEM.getRDC()==gridArray[10].getRDC())
					return true;
    		}
    		break;
    	case 3:
    		if(COLUMN==0){
    			if (ITEM.getRDC()==gridArray[13].getRDC() && ITEM.getRDC()==gridArray[14].getRDC())
					return true;
    		}else if(COLUMN==1){
    			if (ITEM.getRDC()==gridArray[14].getRDC()&&ITEM.getRDC()==gridArray[15].getRDC())
					return true;
    			else if(ITEM.getRDC()==gridArray[12].getRDC()&&ITEM.getRDC()==gridArray[14].getRDC())
    				return true;
    		}else if(COLUMN==2){
    			if (ITEM.getRDC()==gridArray[12].getRDC()&&ITEM.getRDC()==gridArray[13].getRDC())
    				return true;
    			if (ITEM.getRDC()==gridArray[13].getRDC()&&ITEM.getRDC()==gridArray[15].getRDC())
    				return true;
    		}else if(COLUMN==3){
    			if (ITEM.getRDC()==gridArray[13].getRDC() && ITEM.getRDC()==gridArray[14].getRDC())
					return true;
    		}
    		break;
    	// ~~~ hardmode ~~~
    	case 4:
    		if(!isHardMode)
    			break;
    		if(COLUMN==0){
    			if (ITEM.getRDC()==gridArray[17].getRDC() && ITEM.getRDC()==gridArray[18].getRDC())
					return true;
    		}else if(COLUMN==1){
    			if (ITEM.getRDC()==gridArray[18].getRDC()&&ITEM.getRDC()==gridArray[19].getRDC())
					return true;
    			else if(ITEM.getRDC()==gridArray[16].getRDC()&&ITEM.getRDC()==gridArray[18].getRDC())
    				return true;
    		}else if(COLUMN==2){
    			if (ITEM.getRDC()==gridArray[16].getRDC()&&ITEM.getRDC()==gridArray[17].getRDC())
    				return true;
    			if (ITEM.getRDC()==gridArray[17].getRDC()&&ITEM.getRDC()==gridArray[19].getRDC())
    				return true;
    		}else if(COLUMN==3){
    			if (ITEM.getRDC()==gridArray[18].getRDC() && ITEM.getRDC()==gridArray[17].getRDC())
					return true;
    		}
    		break;
    	}
    	// ~~~ end hardmode ~~~
    	// ------------------------------------------------ CHECK VERTICAL ------------------------------------------
    	switch(ROW){
    	
    	
		case 1:
			if(COLUMN==0){
				if (ITEM.getRDC()==gridArray[0].getRDC() && ITEM.getRDC()==gridArray[8].getRDC())
					return true;
				else if (ITEM.getRDC()==gridArray[12].getRDC() && ITEM.getRDC()==gridArray[8].getRDC())
					return true;
			}else if(COLUMN==1){
				if (ITEM.getRDC()==gridArray[1].getRDC() && ITEM.getRDC()==gridArray[9].getRDC())
					return true;
				else if (ITEM.getRDC()==gridArray[9].getRDC() && ITEM.getRDC()==gridArray[13].getRDC())
					return true;
			}else if(COLUMN==2){
				if (ITEM.getRDC()==gridArray[2].getRDC() && ITEM.getRDC()==gridArray[10].getRDC())
					return true;
				else if (ITEM.getRDC()==gridArray[10].getRDC() && ITEM.getRDC()==gridArray[14].getRDC())
					return true;
			}else if(COLUMN==3){
				if (ITEM.getRDC()==gridArray[3].getRDC() && ITEM.getRDC()==gridArray[11].getRDC())
					return true;
				else if (ITEM.getRDC()==gridArray[11].getRDC() && ITEM.getRDC()==gridArray[15].getRDC())
					return true;
			}
			break;
			
		case 2:
			if(COLUMN==0){
				if (ITEM.getRDC()==gridArray[12].getRDC() && ITEM.getRDC()==gridArray[4].getRDC())
					return true;
				else if (ITEM.getRDC()==gridArray[0].getRDC() && ITEM.getRDC()==gridArray[4].getRDC())
					return true;
				if(isHardMode)
					if (ITEM.getRDC()==gridArray[12].getRDC() && ITEM.getRDC()==gridArray[16].getRDC())
						return true;
			}else if(COLUMN==1){
				if (ITEM.getRDC()==gridArray[5].getRDC() && ITEM.getRDC()==gridArray[1].getRDC())
					return true;
				else if (ITEM.getRDC()==gridArray[5].getRDC() && ITEM.getRDC()==gridArray[13].getRDC())
					return true;
				if(isHardMode)
					if (ITEM.getRDC()==gridArray[17].getRDC() && ITEM.getRDC()==gridArray[13].getRDC())
						return true;
			}else if(COLUMN==2){
				if (ITEM.getRDC()==gridArray[14].getRDC() && ITEM.getRDC()==gridArray[6].getRDC())
					return true;
				else if (ITEM.getRDC()==gridArray[2].getRDC() && ITEM.getRDC()==gridArray[6].getRDC())
					return true;
				if(isHardMode)
					if (ITEM.getRDC()==gridArray[14].getRDC() && ITEM.getRDC()==gridArray[18].getRDC())
						return true;
				
			}else if(COLUMN==3){
				if (ITEM.getRDC()==gridArray[3].getRDC() && ITEM.getRDC()==gridArray[7].getRDC())
					return true;
				else if (ITEM.getRDC()==gridArray[7].getRDC() && ITEM.getRDC()==gridArray[15].getRDC())
					return true;
				if(isHardMode)
					if (ITEM.getRDC()==gridArray[19].getRDC() && ITEM.getRDC()==gridArray[15].getRDC())
						return true;
			}
			break;
		
    	
		case 3:
			if(COLUMN==0){
				if (ITEM.getRDC()==gridArray[8].getRDC() && ITEM.getRDC()==gridArray[4].getRDC())
					return true;
				if(isHardMode)
					if (ITEM.getRDC()==gridArray[8].getRDC() && ITEM.getRDC()==gridArray[16].getRDC())
						return true;
			}else if(COLUMN==1){
				if (ITEM.getRDC()==gridArray[5].getRDC() && ITEM.getRDC()==gridArray[9].getRDC())
					return true;
				if(isHardMode)
					if (ITEM.getRDC()==gridArray[17].getRDC() && ITEM.getRDC()==gridArray[9].getRDC())
						return true;
			}else if(COLUMN==2){
				if (ITEM.getRDC()==gridArray[10].getRDC() && ITEM.getRDC()==gridArray[6].getRDC())
					return true;
				if(isHardMode)
					if (ITEM.getRDC()==gridArray[10].getRDC() && ITEM.getRDC()==gridArray[18].getRDC())
						return true;
				
			}else if(COLUMN==3){
				if (ITEM.getRDC()==gridArray[11].getRDC() && ITEM.getRDC()==gridArray[7].getRDC())
					return true;
				if(isHardMode)
					if (ITEM.getRDC()==gridArray[19].getRDC() && ITEM.getRDC()==gridArray[11].getRDC())
						return true;
			}
			break;
			
		case 0:
    		if(COLUMN==0){
    			if (ITEM.getRDC()==gridArray[4].getRDC() && ITEM.getRDC()==gridArray[8].getRDC())
    				return true;
    			if(isHardMode)
    				if (ITEM.getRDC()==gridArray[16].getRDC() && ITEM.getRDC()==gridArray[8].getRDC())
    					return true;
    		}else if(COLUMN==1){
    			if (ITEM.getRDC()==gridArray[5].getRDC() && ITEM.getRDC()==gridArray[9].getRDC())
    				return true;
    			if(isHardMode)
    				if (ITEM.getRDC()==gridArray[17].getRDC() && ITEM.getRDC()==gridArray[9].getRDC())
    					return true;
    		}else if(COLUMN==2){
    			if (ITEM.getRDC()==gridArray[6].getRDC() && ITEM.getRDC()==gridArray[10].getRDC())
    				return true;
    			if(isHardMode)
    				if (ITEM.getRDC()==gridArray[18].getRDC() && ITEM.getRDC()==gridArray[10].getRDC())
    					return true;
    		}else if(COLUMN==3){
    			if (ITEM.getRDC()==gridArray[7].getRDC() && ITEM.getRDC()==gridArray[11].getRDC())
    				return true;
    			if(isHardMode)
	    			if (ITEM.getRDC()==gridArray[19].getRDC() && ITEM.getRDC()==gridArray[11].getRDC())
	    				return true;
    		}
    		break;
	
    	// ---- hard mode ----
		case 4:
			if(!isHardMode) break;
			if(COLUMN==0){
				if (ITEM.getRDC()==gridArray[12].getRDC() && ITEM.getRDC()==gridArray[8].getRDC())
					return true;
			}else if(COLUMN==1){
				if (ITEM.getRDC()==gridArray[13].getRDC() && ITEM.getRDC()==gridArray[9].getRDC())
					return true;
			}else if(COLUMN==2){
				if (ITEM.getRDC()==gridArray[14].getRDC() && ITEM.getRDC()==gridArray[10].getRDC())
					return true;
			}else if(COLUMN==3){
				if (ITEM.getRDC()==gridArray[15].getRDC() && ITEM.getRDC()==gridArray[11].getRDC())
					return true;
			}
			break;
    	}
    	return false;
    }

}
