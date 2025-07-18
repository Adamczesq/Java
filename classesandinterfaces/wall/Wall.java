package classesandinterfaces.wall;

import lombok.Data;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
public class Wall implements Structure {
    private List<Block> blocks;

    public Wall(List<Block> blocks) {
        this.blocks = blocks;
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return blocks.stream().filter(block -> block.getColor().equals(color)).findFirst();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return blocks.stream().filter(block -> block.getMaterial().equals(material)).collect(Collectors.toList());
    }

    @Override
    public int count() {
        return (int) streamAllBlocks().count();
    }

    private Stream<Block> streamAllBlocks() {
        return blocks.stream().flatMap(this::streamBlock);
    }

    private Stream<Block> streamBlock(Block block) {
        if (block instanceof final CompositeBlock composite) {
            return Stream.concat(
                    Stream.of(composite),
                    composite.getBlocks().stream().flatMap(this::streamBlock)
            );
        }

        return Stream.of(block);
    }
}
