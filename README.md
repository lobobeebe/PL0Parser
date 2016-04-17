# PL0Parser
Authors: Cody Beebe, Blake Klasing
Date: 02/16/2016
Course: COP 5021
PL0 Parser project for COP5021 HW 2.4

# Build
ant

# Test
ant test

# Collaboration
(i) participants: Cody Beebe, Blake Klasing
(ii) Each person actively contributed to and fully understands the solution.

Set<ElementaryBlock> ret = SetRepUtility.emptySet();
for(ProcS proc : ProcStar())
{
    if(proc.getName().equals(getProcName()))
    {
        ret.addAll(proc.getBlock().blocks());
    }
}
ret.add(this);
return ret;
