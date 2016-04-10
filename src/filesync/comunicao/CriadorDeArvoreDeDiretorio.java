/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesync.comunicao;

import filesync.persistencia.Tree;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;

/**
 *
 * @author Francisco
 */
public class CriadorDeArvoreDeDiretorio extends SimpleFileVisitor<Path> {
    private Path raiz;        
    private Tree<Path> arvoreDoDiretorio;
    private Tree<Path> arvoreDoDiretorioRemoto;    
    
    public CriadorDeArvoreDeDiretorio(Path raiz) {        
        arvoreDoDiretorio = new Tree<>(raiz);
    }
    
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {        
        if (dir.toFile().isHidden())
            return FileVisitResult.SKIP_SUBTREE;                
        
        return FileVisitResult.CONTINUE;
    }
    
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (file.toFile().isDirectory())
            arvoreDoDiretorio.addLeaf(raiz, file);        
        return FileVisitResult.CONTINUE;
    }
            
    @Override
    public FileVisitResult visitFileFailed(Path file, IOException e)
            throws IOException {
        System.err.printf("Arquivo visitado falho + %s", file);        
        return FileVisitResult.SKIP_SUBTREE;
    }
    
    public String verArvoreCriada() {
        return arvoreDoDiretorio.toString();
    }
    
    public static void main(String[] args) throws IOException {
        
        String caminho = "C:\\Users\\Francisco\\Documents\\TesteArquivos";
        Path path;
        path = FileSystems.getDefault().getPath(caminho);
        
        CriadorDeArvoreDeDiretorio criadorDeArvore = new CriadorDeArvoreDeDiretorio(path);
        
        Files.walkFileTree(path, criadorDeArvore);        
        
        System.out.println(criadorDeArvore.verArvoreCriada());
        
        Iterable<Path> dirs = path;
        for (Path name: dirs) {
            System.err.println(name);
        }       
    }            
}
