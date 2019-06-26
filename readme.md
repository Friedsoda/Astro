# Astro 
注意GameController是单例的。
分了很多个Handler，但是几乎所有Handler是空的。  
写了一种AI："温和的"、"更趋向于奔向终点"、"不会主动击杀其他玩家"的AI类型。  
使用了Coordinate类进（单例）行坐标转换，Chess中的静态变量已经移到Coordiinate中。  
只要等一波动画，就可以跑本地飞行棋了。  
