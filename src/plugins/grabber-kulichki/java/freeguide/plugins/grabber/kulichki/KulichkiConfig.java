package freeguide.plugins.grabber.kulichki;

import freeguide.common.lib.fgspecific.data.TVChannelsSelection;
import freeguide.common.lib.fgspecific.data.TVChannelsSet;

/**
 * Class for store config information.
 *
 * @author Alex Buloichik (alex73 at zaval.org)
 */
public class KulichkiConfig
{

    /** DOCUMENT ME! */
    public TVChannelsSelection channels = new TVChannelsSelection(  );

    /**
     * Creates a new Config object.
     */
    public KulichkiConfig(  )
    {
        channels.allChannels.add( 
            new TVChannelsSet.Channel( "kulichki", "All" ) );

    }

    /**
     * DOCUMENT_ME!
     *
     * @return DOCUMENT_ME!
     */
    public Object clone(  )
    {

        KulichkiConfig result = new KulichkiConfig(  );

        result.channels = (TVChannelsSelection)channels.clone(  );

        return result;

    }
}
