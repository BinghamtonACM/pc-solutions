n={'zero':0,'one':1,'two':2,'three':3,'four':4,'five':5,'six':6,'seven':7,'eight':8,'nine':9,'ten':10,'eleven':11,'twelve':12,'thirteen':13,'fourteen':14,'fifteen':15,'sixteen':16,'seventeen':17,'eighteen':18,'nineteen':19,'plus':'+','divided':'/','minus':'-','times':'*','by':'','twenty':20,'thirty':30,'forty':40,'fifty':50,'sixty':60,'seventy':70,'eighty':80,'ninety':90}
a,o=input().split(),[''];r=range
for w in a:
 if n[w]in r(19)and o[-1]in r(20,91,10):o[-1]+=n[w]
 else:o+=[n[w]]
print(int(eval(''.join([str(x)for x in o]))))
