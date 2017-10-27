package com.example.gameModel.classes.VisitorAssignment;

/**
 * Created by Mitchell Foote on 10/27/2017.
 */

public class DirectoryStructure
{
    private DirectoryStructureNode root;
    public int getSize(){

    }
    public String[] getFileNames(){

    }

}
abstract class DirectoryStructureNode{

}
public class FileNode extends DirectoryStructureNode{
    public String getName(){}
    public int getSize(){}
}

public class DirectoryNode extends DirectoryStructureNode{
    public FileNode[] getFileNodes(){}
    public DirectoryNode[] getDirectoryNodes(){}
}