import React, { Component } from "react";
import Dropzone from 'react-dropzone'

const baseStyle = {
    flex: 1,
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    padding: '20px',
    borderWidth: 2,
    borderRadius: 2,
    borderColor: '#eeeeee',
    borderStyle: 'dashed',
    backgroundColor: '#fafafa',
    color: '#bdbdbd',
    outline: 'none',
    transition: 'border .24s ease-in-out',
};

export class DragNDrop extends Component {
    constructor() {
        super();
        this.onDrop = (files) => {
            this.setState({ files })
        };
        this.state = {
            files: []
        };
    }

    render() {
        const files = this.state.files.map(file => {
            this.props.assignFile(file);
            return (
                <li key={file.name}>
                    {file.name} - {file.size} bytes
                </li>
            )
        });

        return (
            <Dropzone multiple={false}
                onDrop={this.onDrop}>
                {({ getRootProps, getInputProps }) => (
                    <section>
                        <div style={baseStyle} {...getRootProps({ className: 'dropzone' })}>
                            <input {...getInputProps()} />
                            <p>Arrastra un archivo o haz click para seleccionarlo.</p>
                        </div>
                        <aside>
                            <ul>{files}</ul>
                        </aside>
                    </section>
                )}
            </Dropzone>
        );
    }
}

export default DragNDrop;