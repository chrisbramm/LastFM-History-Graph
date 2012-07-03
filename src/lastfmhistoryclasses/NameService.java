package lastfmhistoryclasses;

import java.util.Arrays;
import java.util.List;

import sas.samples.CompletionService;

public class NameService implements CompletionService<String> {

    /** Our name data. */
    private List<String> data;

    /**
     * Create a new <code>NameService</code> and populate it.
     */
    public NameService(List<String> trackListInput) {
        this.data = trackListInput;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        for (String o : data) {
            b.append(o).append("\n");
        }
        return b.toString();
    }

    /** {@inheritDoc} */
    public String autoComplete(String startsWith) {
        // Naive implementation, but good enough for the sample
        String hit = null;
        for (String o : data) {
            if (o.startsWith(startsWith)) {
                // CompletionService contract states that we only
                // should return completion for unique hits.
                if (hit == null) {
                    hit = o;
                } else {
                    hit = null;
                    break;
                }
            }
        }
        return hit;
    }

}