import bogglevalidator.BoggleValidator;
import org.junit.Assert;
import org.junit.Test;


public class BoggleValidatorTest
{
    private final char[][] boggleBoard4x4 = {
            {'I','L','A','W'},
            {'B','N','G','E'},
            {'I','U','A','O'},
            {'A','S','R','L'} };

    private final char[][] boggleBoard3x3 = {
            {'I','L','A'},
            {'B','N','G'},
            {'I','U','A'}};

    private final char[][] boggleBoard5x5 = {
            {'I','L','A','Z','Q'},
            {'B','N','G','S','A'},
            {'E','R','U','H','Y'},
            {'F','E','I','I','E'},
            {'T','C','O','J','G'}};

    @Test
    public void boggleValidatorValidStrings()
    {
        BoggleValidator boggleValidator = new BoggleValidator();

        Assert.assertTrue(boggleValidator.isValidGuess(boggleBoard4x4, "BINGO"));
        Assert.assertTrue(boggleValidator.isValidGuess(boggleBoard4x4, "LINGO"));
        Assert.assertTrue(boggleValidator.isValidGuess(boggleBoard4x4, "ILNBIA"));
        Assert.assertTrue(boggleValidator.isValidGuess(boggleBoard4x4, "SAGE"));
        Assert.assertTrue(boggleValidator.isValidGuess(boggleBoard4x4, "WEARS"));

        Assert.assertTrue(boggleValidator.isValidGuess(boggleBoard3x3, "BIN"));
        Assert.assertTrue(boggleValidator.isValidGuess(boggleBoard3x3, "LANG"));
        Assert.assertTrue(boggleValidator.isValidGuess(boggleBoard3x3, "GANBIL"));

        Assert.assertTrue(boggleValidator.isValidGuess(boggleBoard5x5, "GUIGE"));
        Assert.assertTrue(boggleValidator.isValidGuess(boggleBoard5x5, "BINER"));
        Assert.assertTrue(boggleValidator.isValidGuess(boggleBoard5x5, "JOIYASGAZ"));
    }

    @Test
    public void boggleValidatorInvalidStringsDueToNoPath()
    {
        BoggleValidator boggleValidator = new BoggleValidator();

        Assert.assertFalse(boggleValidator.isValidGuess(boggleBoard4x4, "BUNGIE"));
        Assert.assertFalse(boggleValidator.isValidGuess(boggleBoard4x4, "BINS"));
        Assert.assertFalse(boggleValidator.isValidGuess(boggleBoard4x4, "ROGUE"));
        Assert.assertFalse(boggleValidator.isValidGuess(boggleBoard4x4, "WAGER"));

        Assert.assertFalse(boggleValidator.isValidGuess(boggleBoard3x3, "LAGI"));
        Assert.assertFalse(boggleValidator.isValidGuess(boggleBoard3x3, "BILU"));
        Assert.assertFalse(boggleValidator.isValidGuess(boggleBoard3x3, "AGAIN"));

        Assert.assertFalse(boggleValidator.isValidGuess(boggleBoard5x5, "QAYIB"));
        Assert.assertFalse(boggleValidator.isValidGuess(boggleBoard5x5, "ZAGO"));
        Assert.assertFalse(boggleValidator.isValidGuess(boggleBoard5x5, "COIHZA"));
    }

    @Test
    public void boggleValidatorInvalidStringsDueToCellReuseRule()
    {
        BoggleValidator boggleValidator = new BoggleValidator();
        Assert.assertFalse(boggleValidator.isValidGuess(boggleBoard4x4, "SINUS"));
        Assert.assertFalse(boggleValidator.isValidGuess(boggleBoard4x4, "LANGAG"));
        Assert.assertFalse(boggleValidator.isValidGuess(boggleBoard4x4, "LANGAG"));

        Assert.assertFalse(boggleValidator.isValidGuess(boggleBoard3x3, "LANGAG"));
        Assert.assertFalse(boggleValidator.isValidGuess(boggleBoard3x3, "BINIB"));
        Assert.assertFalse(boggleValidator.isValidGuess(boggleBoard3x3, "GUNG"));

        Assert.assertFalse(boggleValidator.isValidGuess(boggleBoard5x5, "SHIGEH"));
        Assert.assertFalse(boggleValidator.isValidGuess(boggleBoard5x5, "GANERN"));
        Assert.assertFalse(boggleValidator.isValidGuess(boggleBoard5x5, "JOIII"));
    }

    @Test
    public void boggleValidatorInvalidStringsDueToMissingCharacters()
    {
        BoggleValidator boggleValidator = new BoggleValidator();
        Assert.assertFalse(boggleValidator.isValidGuess(boggleBoard4x4, "LAZER"));
        Assert.assertFalse(boggleValidator.isValidGuess(boggleBoard4x4, "HANGERS"));
        Assert.assertFalse(boggleValidator.isValidGuess(boggleBoard4x4, "JAVA"));

        Assert.assertFalse(boggleValidator.isValidGuess(boggleBoard3x3, "LANGE"));
        Assert.assertFalse(boggleValidator.isValidGuess(boggleBoard3x3, "LINT"));
        Assert.assertFalse(boggleValidator.isValidGuess(boggleBoard3x3, "JAVA"));

        Assert.assertFalse(boggleValidator.isValidGuess(boggleBoard5x5, "GUIVE"));
        Assert.assertFalse(boggleValidator.isValidGuess(boggleBoard5x5, "BINERJ"));
        Assert.assertFalse(boggleValidator.isValidGuess(boggleBoard5x5, "DENT"));
    }
}